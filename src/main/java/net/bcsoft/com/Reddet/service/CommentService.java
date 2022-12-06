package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.CommentDTO;
import net.bcsoft.com.Reddet.exception.PostNotFoundException;
import net.bcsoft.com.Reddet.exception.SpringReddetException;
import net.bcsoft.com.Reddet.mapper.CommentMapper;
import net.bcsoft.com.Reddet.model.Comment;
import net.bcsoft.com.Reddet.model.NotificationEmail;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.repository.CommentRepository;
import net.bcsoft.com.Reddet.repository.PostRepo;
import net.bcsoft.com.Reddet.repository.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepo postRepository;
    private final UserRepo userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDTO commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.Build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findAllByPost(post)
                .stream()
                .map(commentMapper::mapToDTO).collect(toList());
    }

    public List<CommentDTO> getAllCommentsForUser(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDTO)
                .collect(toList());
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringReddetException("Comments contains unacceptable language");
        }
        return false;
    }
}


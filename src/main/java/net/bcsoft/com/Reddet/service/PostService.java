package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bcsoft.com.Reddet.DTO.PostRequest;
import net.bcsoft.com.Reddet.DTO.PostResponse;
import net.bcsoft.com.Reddet.exception.PostNotFoundException;
import net.bcsoft.com.Reddet.exception.SubReddetNotFoundException;
import net.bcsoft.com.Reddet.mapper.PostMapper;
import net.bcsoft.com.Reddet.model.SubReddet;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.repository.PostRepo;
import net.bcsoft.com.Reddet.repository.SubReddetRepo;
import net.bcsoft.com.Reddet.repository.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepo postRepository;
    private final SubReddetRepo subreddetRepository;
    private final UserRepo userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        SubReddet subreddet = subreddetRepository.findByName(postRequest.getSubReddetName())
                .orElseThrow(() -> new SubReddetNotFoundException(postRequest.getSubReddetName()));
        postRepository.save(postMapper.mapDTOToModel(postRequest, subreddet, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDTO(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        SubReddet subReddet = subreddetRepository.findById(subredditId)
                .orElseThrow(() -> new SubReddetNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubReddet(subReddet);
        return posts.stream().map(postMapper::mapToDTO).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }
}

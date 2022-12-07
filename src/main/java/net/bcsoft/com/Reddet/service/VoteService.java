package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.VoteDTO;
import net.bcsoft.com.Reddet.exception.PostNotFoundException;
import net.bcsoft.com.Reddet.exception.SpringReddetException;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.Vote;
import net.bcsoft.com.Reddet.model.VoteType;
import net.bcsoft.com.Reddet.repository.PostRepo;
import net.bcsoft.com.Reddet.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepo postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDTO voteDTO) {
        Post post = postRepository.findById(voteDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDTO.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByIdVoteDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDTO.getVoteType())) {
            throw new SpringReddetException("You have already "
                    + voteDTO.getVoteType() + "'d for this post");
        }
        if (VoteType.UP_VOTE.equals(voteDTO.getVoteType())) {
            post.setVoteCounter(post.getVoteCounter() + 1);
        } else {
            post.setVoteCounter(post.getVoteCounter() - 1);
        }
        voteRepository.save(mapToVote(voteDTO, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDTO voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}

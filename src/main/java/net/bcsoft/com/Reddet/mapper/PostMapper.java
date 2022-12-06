package net.bcsoft.com.Reddet.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import net.bcsoft.com.Reddet.DTO.PostRequest;
import net.bcsoft.com.Reddet.DTO.PostResponse;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.SubReddet;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.model.VoteType;
import net.bcsoft.com.Reddet.repository.CommentRepository;
import net.bcsoft.com.Reddet.repository.VoteRepository;
import net.bcsoft.com.Reddet.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;

@Mapper (componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private AuthService authService;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target="description", source = "postRequest.description")
    @Mapping(target="subReddet", source = "subReddet")
    @Mapping(target="voteCount",constant = "0")
    @Mapping(target= "user", source = "user")
    public abstract Post mapDTOToModel(PostRequest postRequest, SubReddet subReddet, User user);
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subReddetName", source = "subReddet.name")
    @Mapping(target = "username", source ="user.username")
    //@Mapping(target = "commentCount", expression = "java(commentCount(post))")
    //@Mapping(target = "duration", expression = "java(getDuration(post))")
    //@Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    //@Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDTO(Post post);

    //Integer commentCount(Post post){
        //return commentRepository.findAllByPost(post).size();
    //}

    //String getDuration(Post post){
        //return TimeAgo.using(post.getCreationDate().toEpochMilli());
    //}

    //boolean isPostUpVoted(Post post){

    //}

    //boolean isPostDownVoted(Post post){

    //}

    //private boolean checkVoteType(Post post, VoteType voteType){
        //if
    //}
}
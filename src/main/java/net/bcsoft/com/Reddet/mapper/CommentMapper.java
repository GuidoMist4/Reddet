package net.bcsoft.com.Reddet.mapper;

import net.bcsoft.com.Reddet.DTO.CommentDTO;
import net.bcsoft.com.Reddet.model.Comment;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "text", source = "commentDTO.text")
    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDTO commentDTO, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentDTO mapToDTO(Comment comment);
}

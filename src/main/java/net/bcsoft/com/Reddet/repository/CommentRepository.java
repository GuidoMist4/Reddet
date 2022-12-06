package net.bcsoft.com.Reddet.repository;


import net.bcsoft.com.Reddet.model.Comment;
import net.bcsoft.com.Reddet.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {
    public List<Comment> findAllByPost(Post post);
}


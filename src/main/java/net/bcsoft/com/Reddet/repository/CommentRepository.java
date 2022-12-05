package net.bcsoft.com.Reddet.repository;


import net.bcsoft.com.Reddet.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {
}


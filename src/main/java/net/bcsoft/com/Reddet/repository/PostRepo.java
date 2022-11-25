package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}

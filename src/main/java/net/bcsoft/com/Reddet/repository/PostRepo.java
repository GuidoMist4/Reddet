package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.SubReddet;
import net.bcsoft.com.Reddet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findAllBySubReddet(SubReddet subReddet);

    List<Post> findByUser(User user);
}

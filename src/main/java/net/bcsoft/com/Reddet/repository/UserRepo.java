package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findUserByUsername(String username);
}

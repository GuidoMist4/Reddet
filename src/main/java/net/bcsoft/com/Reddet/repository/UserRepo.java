package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findUserByUsername(String username);
}

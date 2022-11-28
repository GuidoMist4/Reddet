package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}

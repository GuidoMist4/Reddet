package net.bcsoft.com.Reddet.repository;


import net.bcsoft.com.Reddet.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationTokenRepository  extends CrudRepository<VerificationToken, Long> {
}


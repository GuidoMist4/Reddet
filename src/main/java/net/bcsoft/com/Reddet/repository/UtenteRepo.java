package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepo extends JpaRepository<Utente, Long> {
}

package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.model.SubReddet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubReddetRepo extends JpaRepository<SubReddet,Long> {
}

package net.bcsoft.com.Reddet.repository;

import net.bcsoft.com.Reddet.DTO.SubReddetDTO;
import net.bcsoft.com.Reddet.model.SubReddet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubReddetRepo extends JpaRepository<SubReddet,Long> {

}

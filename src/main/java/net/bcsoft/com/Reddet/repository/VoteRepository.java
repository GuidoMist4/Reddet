package net.bcsoft.com.Reddet.repository;
import net.bcsoft.com.Reddet.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface  VoteRepository  extends CrudRepository<Vote, Long> {
    
    }

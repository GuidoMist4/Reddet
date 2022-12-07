package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVote;
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name="post_fk", referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_fk", referencedColumnName = "id")
    private User user;
}

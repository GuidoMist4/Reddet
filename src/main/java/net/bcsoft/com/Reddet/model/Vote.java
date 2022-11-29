package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVote;

    @ManyToOne
    @JoinColumn(name="post_fk", referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name="utente_fk", referencedColumnName = "id")
    private User user;
}

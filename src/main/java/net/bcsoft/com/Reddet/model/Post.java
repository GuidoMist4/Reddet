package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long postId;
    @NotBlank
    private String postName;
    private String url;
    @NotBlank
    private String description;
    private int voteCounter;
    private Instant creationDate;

    @ManyToOne
    @JoinColumn(name="user_fk", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="subReddet_fk", referencedColumnName = "subId")
    private SubReddet subReddet;

    @OneToMany(mappedBy="post")
    private List<Vote> votes=new ArrayList<>();

    @OneToMany(mappedBy="post")
    private List<Comment> comments=new ArrayList<>();
}

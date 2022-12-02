package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    @NotBlank
    private String text;
    private Instant creationDate;

    @ManyToOne
    @JoinColumn(name="user_fk", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_fk", referencedColumnName = "postId")
    private Post post;
}

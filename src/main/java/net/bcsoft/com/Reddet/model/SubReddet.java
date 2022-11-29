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
public class SubReddet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Instant creationDate;

    @OneToMany(mappedBy="subReddet")
    private List<Post> posts=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_fk", referencedColumnName = "id")
    private User user;
}



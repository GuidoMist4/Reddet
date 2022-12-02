package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    private Instant created;
    private boolean enabled;

    @OneToMany(mappedBy="user")
    private List<Comment> comments= new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Post> posts= new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<SubReddet> subReddet= new ArrayList<>();

    @OneToOne
    @JoinColumn(name="token_fk", referencedColumnName = "idVerificationToken")
    private VerificationToken verificationToken;

    @OneToMany(mappedBy="user")
    private List<Vote> votes= new ArrayList<>();
}

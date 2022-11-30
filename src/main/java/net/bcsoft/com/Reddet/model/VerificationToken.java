package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VerificationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVerificationToken;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String token;
    private Instant expirationDate;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}

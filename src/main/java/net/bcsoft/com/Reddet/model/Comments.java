package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    @NotBlank
    private String text;
    private Instant creationDate;
}

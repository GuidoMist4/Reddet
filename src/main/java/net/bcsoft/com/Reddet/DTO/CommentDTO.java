package net.bcsoft.com.Reddet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private long id;
    private long postId;
    private Instant creationDate;
    private String text;
    private String username;

}

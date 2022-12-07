package net.bcsoft.com.Reddet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private long id;
    private String postName;
    private String url;
    private String description;
    private String username;
    private String subReddetName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    private boolean up_vote;
    private boolean down_vote;

}

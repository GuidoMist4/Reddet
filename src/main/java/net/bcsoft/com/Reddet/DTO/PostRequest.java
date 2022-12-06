package net.bcsoft.com.Reddet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private long postId;
    private String subReddetName;
    private String postName;
    private String url;
    private String description;

}

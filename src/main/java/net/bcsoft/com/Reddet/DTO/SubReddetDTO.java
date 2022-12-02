package net.bcsoft.com.Reddet.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubReddetDTO {
    private long subId;
    private String name;
    private String description;
    private Integer postNumber;
}

package net.bcsoft.com.Reddet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {
    @NotBlank
    private String refreshToken;
    private String username;
}
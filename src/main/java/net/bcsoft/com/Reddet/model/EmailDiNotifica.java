package net.bcsoft.com.Reddet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDiNotifica {

    private String soggetto;
    private String recipiente;
    private String corpo;
}

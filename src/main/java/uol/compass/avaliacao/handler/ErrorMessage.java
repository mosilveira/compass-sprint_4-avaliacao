package uol.compass.avaliacao.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Integer status;
    private String message;
    private LocalDate date;
}

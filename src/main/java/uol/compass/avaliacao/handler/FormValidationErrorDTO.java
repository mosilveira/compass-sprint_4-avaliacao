package uol.compass.avaliacao.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormValidationErrorDTO {

    private String field;
    private String error;
}

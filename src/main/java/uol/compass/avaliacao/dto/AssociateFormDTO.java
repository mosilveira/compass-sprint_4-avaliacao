package uol.compass.avaliacao.dto;

import lombok.Builder;
import lombok.Data;
import uol.compass.avaliacao.entity.Gender;
import uol.compass.avaliacao.entity.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class AssociateFormDTO {

    @NotBlank
    private String name;

    @NotNull
    private Position position;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Gender gender;
}

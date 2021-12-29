package uol.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.enums.Gender;
import uol.compass.avaliacao.enums.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

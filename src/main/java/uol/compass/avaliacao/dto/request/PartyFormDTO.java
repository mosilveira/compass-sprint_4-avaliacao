package uol.compass.avaliacao.dto.request;

import lombok.Builder;
import lombok.Data;
import uol.compass.avaliacao.enums.Ideology;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class PartyFormDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String initials;

    @NotNull
    private Ideology ideology;

    @NotNull
    private LocalDate foundationDate;
}

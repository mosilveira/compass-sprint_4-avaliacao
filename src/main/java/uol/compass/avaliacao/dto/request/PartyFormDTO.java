package uol.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.enums.Ideology;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

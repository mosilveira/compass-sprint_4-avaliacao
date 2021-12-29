package uol.compass.avaliacao.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.enums.Ideology;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyDTO {

    private Long id;

    private String name;

    private String initials;

    private Ideology ideology;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate foundationDate;
}

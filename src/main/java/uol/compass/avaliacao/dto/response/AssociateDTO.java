package uol.compass.avaliacao.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Gender;
import uol.compass.avaliacao.enums.Position;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociateDTO {

    private Long id;

    private String name;

    private Position position;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthDate;

    private Gender gender;

    private Party party;
}

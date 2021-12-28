package uol.compass.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.entity.Gender;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.entity.Position;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssociateDTO {

    private Long id;

    private String name;

    private Position position;

    private LocalDate birthDate;

    private Gender gender;

    private Party party;
}

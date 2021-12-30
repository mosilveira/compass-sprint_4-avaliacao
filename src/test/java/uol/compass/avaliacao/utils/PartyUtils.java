package uol.compass.avaliacao.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Ideology;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartyUtils {

    public static PartyFormDTO createFakeFormDTO() {
        return PartyFormDTO.builder()
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build();
    }

    public static PartyDTO createPartyDTO() {
        return PartyDTO.builder()
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build();
    }

    public static Party createFakeParty() {
        return Party.builder()
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build();
    }

    public static Party createFakePartyWithId() {
        return Party.builder()
                .id(1L)
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build();
    }

    public static List<Party> createPartyList() {
        List<Party> parties = new ArrayList<>();

        parties.add(Party.builder()
                .id(1L)
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        parties.add(Party.builder()
                .id(2L)
                .name("Partido Mockito")
                .initials("PM")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        parties.add(Party.builder()
                .id(3L)
                .name("Partido dos Simulados")
                .initials("PS")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        return parties;
    }

    public static List<PartyDTO> createPartyDTOList() {
        List<PartyDTO> parties = new ArrayList<>();

        parties.add(PartyDTO.builder()
                .name("Partido dos testes")
                .initials("PT")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        parties.add(PartyDTO.builder()
                .name("Partido Mockito")
                .initials("PM")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        parties.add(PartyDTO.builder()
                .name("Partido dos Simulados")
                .initials("PS")
                .ideology(Ideology.DIREITA)
                .foundationDate(LocalDate.now())
                .build());

        return parties;
    }

    public static List<Party> createFilteredPartyList() {
        List<Party> parties = new ArrayList<>();

        parties.add(Party.builder()
                .id(2L)
                .name("Partido Mockito")
                .initials("PM")
                .ideology(Ideology.CENTRO)
                .foundationDate(LocalDate.now())
                .build());

        return parties;
    }

    public static List<PartyDTO> createFilteredPartyDTOList() {
        List<PartyDTO> parties = new ArrayList<>();

        parties.add(PartyDTO.builder()
                .name("Partido Mockito")
                .initials("PM")
                .ideology(Ideology.CENTRO)
                .foundationDate(LocalDate.now())
                .build());

        return parties;
    }

    public static String asJsonString(PartyFormDTO partyFormDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(partyFormDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

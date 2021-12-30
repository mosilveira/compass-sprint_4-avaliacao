package uol.compass.avaliacao.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.enums.Gender;
import uol.compass.avaliacao.enums.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssociateUtils {

    public static AssociateFormDTO createFakeFormDTO(){
        return AssociateFormDTO.builder()
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build();
    }

    public static AssociateDTO createFakeAssociateDTO(){
        return AssociateDTO.builder()
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build();
    }

    public static Associate createFakeAssociate() {
        return Associate.builder()
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build();
    }

    public static Associate createFakeAssociateWithId() {
        return Associate.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build();
    }

    public static List<Associate> createFakeAssociateList() {
        List<Associate> associates = new ArrayList<>();

        associates.add(Associate.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        associates.add(Associate.builder()
                .id(2L)
                .name("Josué")
                .position(Position.GOVERNADOR)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        associates.add(Associate.builder()
                .id(1L)
                .name("Aline")
                .position(Position.PREFEITO)
                .birthDate(LocalDate.now())
                .gender(Gender.FEMININO)
                .build());

        return associates;
    }

    public static List<AssociateDTO> createFakeAssociateDTOList() {
        List<AssociateDTO> associates = new ArrayList<>();

        associates.add(AssociateDTO.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        associates.add(AssociateDTO.builder()
                .id(2L)
                .name("Josué")
                .position(Position.GOVERNADOR)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        associates.add(AssociateDTO.builder()
                .id(1L)
                .name("Aline")
                .position(Position.PREFEITO)
                .birthDate(LocalDate.now())
                .gender(Gender.FEMININO)
                .build());

        return associates;
    }

    public static List<AssociateDTO> createFakeAssociateDTOOrderByNameList() {
        List<AssociateDTO> associates = new ArrayList<>();

        associates.add(AssociateDTO.builder()
                .id(1L)
                .name("Aline")
                .position(Position.PREFEITO)
                .birthDate(LocalDate.now())
                .gender(Gender.FEMININO)
                .build());

        associates.add(AssociateDTO.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        associates.add(AssociateDTO.builder()
                .id(2L)
                .name("Josué")
                .position(Position.GOVERNADOR)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        return associates;
    }

    public static List<Associate> createFakeFilteredByPresidentPositionList() {
        List<Associate> associates = new ArrayList<>();

        associates.add(Associate.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        return associates;
    }

    public static List<AssociateDTO> createFakeFilteredByPresidentPositionListDTO() {
        List<AssociateDTO> associates = new ArrayList<>();

        associates.add(AssociateDTO.builder()
                .id(1L)
                .name("Augusto")
                .position(Position.PRESIDENTE)
                .birthDate(LocalDate.now())
                .gender(Gender.MASCULINO)
                .build());

        return associates;
    }

    public static String asJsonString(AssociateFormDTO associateDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(associateDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

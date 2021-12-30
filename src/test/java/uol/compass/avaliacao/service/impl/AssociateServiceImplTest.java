package uol.compass.avaliacao.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.enums.Position;
import uol.compass.avaliacao.exception.ResourceNotFoundException;
import uol.compass.avaliacao.repository.AssociateRepository;
import uol.compass.avaliacao.utils.AssociateUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AssociateServiceImplTest {

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Test
    void whenGivenFormThenAnAssociateShouldBeCreated() {
        AssociateFormDTO form = AssociateUtils.createFakeFormDTO();
        Associate expectedToSaveAssociate = AssociateUtils.createFakeAssociate();
        Associate expectedSavedAssociate = AssociateUtils.createFakeAssociateWithId();

        Mockito.when(modelMapper.map(form, Associate.class)).thenReturn(expectedToSaveAssociate);
        Mockito.when(associateRepository.save(expectedToSaveAssociate)).thenReturn(expectedSavedAssociate);

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message(String.format("Associate with id %s created!", expectedSavedAssociate.getId()))
                .build();

        MessageResponseDTO messageResponseDTO = associateService.create(form);

        Assertions.assertEquals(expectedMessageResponse, messageResponseDTO);
    }

    @Test
    void whenFindIsCalledWithoutParamThenShouldReturnAllAssociates() {
        List<Associate> associates = AssociateUtils.createFakeAssociateList();
        List<AssociateDTO> expectedAssociateDTOList = AssociateUtils.createFakeAssociateDTOList();

        for (int i = 0; i < associates.size(); i++) {
            Mockito.when(modelMapper.map(associates.get(i), AssociateDTO.class)).thenReturn(expectedAssociateDTOList.get(i));
        }

        Mockito.when(associateRepository.findAll()).thenReturn(associates);

        List<AssociateDTO> associatesDTO = associateService.find(null, null);

        Assertions.assertEquals(expectedAssociateDTOList.size(), associatesDTO.size());
        Assertions.assertEquals(expectedAssociateDTOList, associatesDTO);
    }

    @Test
    void whenFindIsCalledWithSortParamThenShouldReturnAssociatesSortedList() {
        List<Associate> associates = AssociateUtils.createFakeAssociateList();
        List<AssociateDTO> associateDTOList = AssociateUtils.createFakeAssociateDTOList();
        List<AssociateDTO> expectedAssociateDTOList = AssociateUtils.createFakeAssociateDTOOrderByNameList();

        for (int i = 0; i < associates.size(); i++) {
            Mockito.when(modelMapper.map(associates.get(i), AssociateDTO.class)).thenReturn(associateDTOList.get(i));
        }
        Mockito.when(associateRepository.findAll()).thenReturn(associates);

        List<AssociateDTO> associatesDTO = associateService.find(null, "name");

        Assertions.assertEquals(expectedAssociateDTOList.size(), associatesDTO.size());
        Assertions.assertEquals(expectedAssociateDTOList, associatesDTO);
    }

    @Test
    void whenFindIsCalledWithFilterByPositionParamThenShouldReturnAssociatesFilteredByPosition() {
        Position position = Position.PRESIDENTE;
        List<Associate> filteredAssociateList = AssociateUtils.createFakeFilteredByPresidentPositionList();
        List<AssociateDTO> expectedAssociateDTOList = AssociateUtils.createFakeFilteredByPresidentPositionListDTO();

        Mockito.when(associateRepository.findAllByPosition(position)).thenReturn(filteredAssociateList);

        for (int i = 0; i < filteredAssociateList.size(); i++) {
            Mockito.when(modelMapper.map(filteredAssociateList.get(i), AssociateDTO.class)).thenReturn(expectedAssociateDTOList.get(i));
        }

        List<AssociateDTO> associateDTOS = associateService.find(position, null);

        Assertions.assertEquals(expectedAssociateDTOList, associateDTOS);
    }

    @Test
    void whenGivenIdThenReturnAssociateById() {
        Long id = 1L;
        Associate expectedAssociate = AssociateUtils.createFakeAssociateWithId();
        AssociateDTO expectedAssociateDTO = AssociateUtils.createFakeAssociateDTO();

        Mockito.when(associateRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(expectedAssociate));
        Mockito.when(modelMapper.map(expectedAssociate, AssociateDTO.class)).thenReturn(expectedAssociateDTO);

        AssociateDTO actualAssociate = associateService.findById(id);

        Assertions.assertEquals(expectedAssociateDTO, actualAssociate);
    }

    @Test
    void whenGivenNonexistentIdToAssociateThenThrowNotFoundException() {
        Long id = 10L;
        Associate expectedAssociate = AssociateUtils.createFakeAssociateWithId();

        Mockito.when(associateRepository.findById(id)).thenThrow(ResourceNotFoundException.class);

        try {
            associateService.findById(id);
            Mockito.verifyNoInteractions(modelMapper.map(expectedAssociate, AssociateDTO.class));
        } catch (Exception ignored) {}
    }

    @Test
    void whenGivenIdThenUpdateAssociateById() {
        Long id = 1L;
        Associate expectedAssociate = AssociateUtils.createFakeAssociateWithId();
        AssociateFormDTO fakeFormDTO = AssociateUtils.createFakeFormDTO();

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message(String.format("Associate with id %s updated!", expectedAssociate.getId()))
                .build();

        Mockito.when(associateRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(expectedAssociate));
        Mockito.when(modelMapper.map(fakeFormDTO, Associate.class)).thenReturn(expectedAssociate);

        MessageResponseDTO actualMessageResponse = associateService.update(id, fakeFormDTO);

        Assertions.assertEquals(expectedMessageResponse, actualMessageResponse);
    }
}
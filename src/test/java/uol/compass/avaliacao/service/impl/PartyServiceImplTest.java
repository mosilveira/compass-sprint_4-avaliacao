package uol.compass.avaliacao.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.repository.PartyRepository;
import uol.compass.avaliacao.utils.PartyUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PartyServiceImplTest {

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PartyServiceImpl partyService;

    @Test
    void whenGivenFormThenAPartyShouldBeCreated() {
        PartyFormDTO fakeFormDTO = PartyUtils.createFakeFormDTO();
        Party partyToSave = PartyUtils.createFakeParty();
        Party savedParty = PartyUtils.createFakePartyWithId();

        Mockito.when(modelMapper.map(fakeFormDTO, Party.class)).thenReturn(partyToSave);
        Mockito.when(partyRepository.save(partyToSave)).thenReturn(savedParty);

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message(String.format("Party with id %s created!", savedParty.getId()))
                .build();

        MessageResponseDTO messageResponseDTO = partyService.create(fakeFormDTO);

        Assertions.assertEquals(expectedMessageResponse, messageResponseDTO);
    }

    @Test
    void whenFindIsCalledWithoutIdeologyParamThenShouldReturnAllParties() {
        List<Party> parties = PartyUtils.createPartyList();
        List<PartyDTO> expectedPartiesDTOList = PartyUtils.createPartyDTOList();

        for (int i = 0; i < parties.size(); i++) {
            Mockito.when(modelMapper.map(parties.get(i), PartyDTO.class)).thenReturn(expectedPartiesDTOList.get(i));
        }

        Mockito.when(partyRepository.findAll()).thenReturn(parties);

        List<PartyDTO> partyDTO = partyService.find(null);

        Assertions.assertEquals(expectedPartiesDTOList.size(), partyDTO.size());
        Assertions.assertEquals(expectedPartiesDTOList, partyDTO);
    }

    @Test
    void whenFindIsCalledWithFilterByIdeologyParamThenShouldReturnPartiesFilteredByIdeology() {
        Ideology ideology = Ideology.CENTRO;

        List<Party> filteredParties = PartyUtils.createFilteredPartyList();
        List<PartyDTO> filteredDTOParties = PartyUtils.createFilteredPartyDTOList();

        Mockito.when(partyRepository.findAllByIdeology(ideology)).thenReturn(filteredParties);

        for (int i = 0; i < filteredParties.size(); i++) {
            Mockito.when(modelMapper.map(filteredParties.get(i), PartyDTO.class)).thenReturn(filteredDTOParties.get(i));
        }

        List<PartyDTO> partyDTOS = partyService.find(ideology);

        Assertions.assertEquals(filteredDTOParties, partyDTOS);
    }

    @Test
    void whenGivenIdThenReturnPartyById() {
        Long id = 1L;

        Party fakeParty = PartyUtils.createFakePartyWithId();
        PartyDTO partyDTO = PartyUtils.createPartyDTO();

        Mockito.when(partyRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(fakeParty));
        Mockito.when(modelMapper.map(fakeParty, PartyDTO.class)).thenReturn(partyDTO);

        PartyDTO actualParty = partyService.findById(id);

        Assertions.assertEquals(partyDTO, actualParty);
    }
}
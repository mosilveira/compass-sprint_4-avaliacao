package uol.compass.avaliacao.service;

import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.enums.Ideology;

import java.util.List;

public interface PartyService {

    MessageResponseDTO create(PartyFormDTO partyFormDTO);

    List<PartyDTO> find(Ideology ideology);

    PartyDTO findById(Long id);

    MessageResponseDTO update(Long id, PartyFormDTO partyFormDTO);

    void delete(Long id);

    List<AssociateDTO> findByParty(Long id);
}

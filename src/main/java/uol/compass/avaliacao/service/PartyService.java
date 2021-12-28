package uol.compass.avaliacao.service;

import uol.compass.avaliacao.dto.*;
import uol.compass.avaliacao.entity.Ideology;
import uol.compass.avaliacao.entity.Position;

import java.util.List;

public interface PartyService {

    MessageResponseDTO create(PartyFormDTO partyFormDTO);

    List<AssociateDTO> find(Ideology ideology);

    PartyDTO findById(Long id);

    MessageResponseDTO update(Long id, PartyFormDTO partyFormDTO);

    void delete(Long id);
}

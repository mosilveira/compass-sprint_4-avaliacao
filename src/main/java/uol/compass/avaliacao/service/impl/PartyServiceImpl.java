package uol.compass.avaliacao.service.impl;

import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.service.PartyService;

import java.util.List;

public class PartyServiceImpl implements PartyService {

    @Override
    public MessageResponseDTO create(PartyFormDTO partyFormDTO) {
        return null;
    }

    @Override
    public List<AssociateDTO> find(Ideology ideology) {
        return null;
    }

    @Override
    public PartyDTO findById(Long id) {
        return null;
    }

    @Override
    public MessageResponseDTO update(Long id, PartyFormDTO partyFormDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

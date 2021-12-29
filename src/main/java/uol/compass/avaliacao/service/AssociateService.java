package uol.compass.avaliacao.service;

import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.enums.Position;

import java.util.List;

public interface AssociateService {

    MessageResponseDTO create(AssociateFormDTO associateFormDTO);

    List<AssociateDTO> find(Position position, String sort);

    AssociateDTO findById(Long id);

    MessageResponseDTO update(Long id, AssociateFormDTO associateFormDTO);

    void delete(Long id);
}

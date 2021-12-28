package uol.compass.avaliacao.service;

import uol.compass.avaliacao.dto.AssociateDTO;
import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.MessageResponseDTO;
import uol.compass.avaliacao.entity.Position;

import java.util.List;

public interface AssociateService {

    MessageResponseDTO create(AssociateFormDTO associateFormDTO);

    List<AssociateDTO> find(Position position, String sort);
}

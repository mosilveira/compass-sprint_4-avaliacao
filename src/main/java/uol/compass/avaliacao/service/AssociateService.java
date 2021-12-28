package uol.compass.avaliacao.service;

import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.MessageResponseDTO;

public interface AssociateService {

    MessageResponseDTO create(AssociateFormDTO associateFormDTO);
}

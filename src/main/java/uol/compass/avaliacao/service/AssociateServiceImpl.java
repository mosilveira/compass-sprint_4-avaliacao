package uol.compass.avaliacao.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.MessageResponseDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.repository.AssociateRepository;

@Service
public class AssociateServiceImpl implements AssociateService {

    private AssociateRepository associateRepository;

    private ModelMapper modelMapper;

    @Autowired
    public AssociateServiceImpl(AssociateRepository associateRepository, ModelMapper modelMapper) {
        this.associateRepository = associateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageResponseDTO create(AssociateFormDTO associateFormDTO) {
        Associate associateToSave = modelMapper.map(associateFormDTO, Associate.class);
        Associate savedAssociate = this.associateRepository.save(associateToSave);

        return MessageResponseDTO.builder()
                .message("Associate with id " + savedAssociate.getId() + " created!")
                .build();
    }
}

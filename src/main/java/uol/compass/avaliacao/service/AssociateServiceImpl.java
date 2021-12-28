package uol.compass.avaliacao.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.avaliacao.dto.AssociateDTO;
import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.MessageResponseDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.entity.Position;
import uol.compass.avaliacao.exception.ResourceNotFoundException;
import uol.compass.avaliacao.repository.AssociateRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AssociateDTO> find(Position position, String sort) {

        List<Associate> associates;

        if (position == null) {
            associates = this.associateRepository.findAll();
        } else {
            associates = this.associateRepository.findAllByPosition(position);
        }

        if (!(sort == null)) {
            if (sort.equals("name")) {
                associates.sort(Comparator.comparing(Associate::getName));
            }
        }

        return associates.stream().map(associate -> modelMapper.map(associate, AssociateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AssociateDTO findById(Long id) {
        Associate associate = this.associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return modelMapper.map(associate, AssociateDTO.class);
    }
}
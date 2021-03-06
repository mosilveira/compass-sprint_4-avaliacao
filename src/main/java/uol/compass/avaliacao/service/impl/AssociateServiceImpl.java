package uol.compass.avaliacao.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.request.LinkFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Position;
import uol.compass.avaliacao.exception.ResourceNotFoundException;
import uol.compass.avaliacao.repository.AssociateRepository;
import uol.compass.avaliacao.repository.PartyRepository;
import uol.compass.avaliacao.service.AssociateService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AssociateServiceImpl implements AssociateService {

    private AssociateRepository associateRepository;

    private PartyRepository partyRepository;

    private ModelMapper modelMapper;

    @Override
    public MessageResponseDTO create(AssociateFormDTO associateFormDTO) {
        Associate associateToSave = modelMapper.map(associateFormDTO, Associate.class);
        Associate savedAssociate = this.associateRepository.save(associateToSave);

        return MessageResponseDTO.builder()
                .message(String.format("Associate with id %s created!", savedAssociate.getId()))
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

    @Override
    public MessageResponseDTO update(Long id, AssociateFormDTO associateFormDTO) {
        this.associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        Associate associateToUpdate = modelMapper.map(associateFormDTO, Associate.class);
        associateToUpdate.setId(id);

        this.associateRepository.save(associateToUpdate);

        return MessageResponseDTO.builder()
                .message(String.format("Associate with id %s updated!", associateToUpdate.getId()))
                .build();
    }

    @Override
    public void delete(Long id) {
        this.associateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        this.associateRepository.deleteById(id);
    }

    @Override
    public MessageResponseDTO link(LinkFormDTO linkFormDTO) {
        Long associateId = linkFormDTO.getIdAssociado();
        Long partyId = linkFormDTO.getIdPartido();

        Associate associate = this.associateRepository.findById(associateId)
                .orElseThrow(() -> new ResourceNotFoundException(associateId));

        Party party = this.partyRepository.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException(partyId));

        associate.setParty(party);

        return MessageResponseDTO.builder()
                .message(String.format("Associate with id %s was linked to the party with id %s", associate.getId(), party.getId()))
                .build();
    }

    @Override
    public MessageResponseDTO removeParty(Long associateId, Long partyId) {
        Associate associate = this.associateRepository.findById(associateId)
                .orElseThrow(() -> new ResourceNotFoundException(associateId));

        Party party = this.partyRepository.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException(partyId));

        associate.setParty(null);
        this.associateRepository.save(associate);

        return MessageResponseDTO.builder()
                .message(String.format("Party with id %s was removed from associate with id %s", party.getId(), associate.getId()))
                .build();
    }
}
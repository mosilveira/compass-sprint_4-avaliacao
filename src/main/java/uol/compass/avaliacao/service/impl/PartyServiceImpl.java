package uol.compass.avaliacao.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.entity.Associate;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.exception.ResourceNotFoundException;
import uol.compass.avaliacao.repository.AssociateRepository;
import uol.compass.avaliacao.repository.PartyRepository;
import uol.compass.avaliacao.service.PartyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    private AssociateRepository associateRepository;

    private ModelMapper modelMapper;

    @Override
    public MessageResponseDTO create(PartyFormDTO partyFormDTO) {
        Party partyToSave = modelMapper.map(partyFormDTO, Party.class);
        Party savedParty = this.partyRepository.save(partyToSave);

        return MessageResponseDTO.builder()
                .message(String.format("Party with id %s created!", savedParty.getId()))
                .build();
    }

    @Override
    public List<PartyDTO> find(Ideology ideology) {
        List<Party> parties;

        if (ideology == null) {
            parties = this.partyRepository.findAll();
        } else {
            parties = this.partyRepository.findAllByIdeology(ideology);
        }

        return parties.stream().map(party -> modelMapper.map(party, PartyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PartyDTO findById(Long id) {
        Party party = this.partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return modelMapper.map(party, PartyDTO.class);
    }

    @Override
    public MessageResponseDTO update(Long id, PartyFormDTO partyFormDTO) {
        this.partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        Party partyToUpdate = modelMapper.map(partyFormDTO, Party.class);
        partyToUpdate.setId(id);

        this.partyRepository.save(partyToUpdate);

        return MessageResponseDTO.builder()
                .message(String.format("Party with id %s updated!", partyToUpdate.getId()))
                .build();
    }

    @Override
    public void delete(Long id) {
        this.partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        this.partyRepository.flush();

        this.partyRepository.deleteById(id);
    }

    @Override
    public List<AssociateDTO> findByParty(Long id) {
        Party party = this.partyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        List<Associate> associates = this.associateRepository.findAllByPartyName(party.getName());

        return associates.stream().map(associate -> modelMapper.map(associate, AssociateDTO.class))
                .collect(Collectors.toList());
    }
}
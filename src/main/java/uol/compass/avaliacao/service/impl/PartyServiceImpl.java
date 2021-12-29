package uol.compass.avaliacao.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.entity.Party;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.exception.ResourceNotFoundException;
import uol.compass.avaliacao.repository.PartyRepository;
import uol.compass.avaliacao.service.PartyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository, ModelMapper modelMapper) {
        this.partyRepository = partyRepository;
        this.modelMapper = modelMapper;
    }

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

        this.partyRepository.deleteById(id);
    }
}

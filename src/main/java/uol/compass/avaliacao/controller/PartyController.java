package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uol.compass.avaliacao.dto.request.PartyFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.dto.response.PartyDTO;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.service.impl.PartyServiceImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartyController {

    private PartyServiceImpl partyService;

    @Autowired
    public PartyController(PartyServiceImpl partyService) {
        this.partyService = partyService;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid PartyFormDTO partyFormDTO) {
        return this.partyService.create(partyFormDTO);
    }

    @GetMapping
    public List<PartyDTO> find(@RequestParam(required = false) Ideology ideology){
        return this.partyService.find(ideology);
    }

    @GetMapping("/{id}")
    public PartyDTO findById(@PathVariable Long id) {
        return this.partyService.findById(id);
    }

    @GetMapping("/{id}/associados")
    public List<AssociateDTO> findByParty(@PathVariable Long id) {
        return this.partyService.findByParty(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid PartyFormDTO partyFormDTO) {
        return this.partyService.update(id, partyFormDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.partyService.delete(id);
    }
}

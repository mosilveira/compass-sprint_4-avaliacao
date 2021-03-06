package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uol.compass.avaliacao.dto.request.AssociateFormDTO;
import uol.compass.avaliacao.dto.request.LinkFormDTO;
import uol.compass.avaliacao.dto.response.AssociateDTO;
import uol.compass.avaliacao.dto.response.MessageResponseDTO;
import uol.compass.avaliacao.enums.Position;
import uol.compass.avaliacao.service.impl.AssociateServiceImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociateController {

    private AssociateServiceImpl associateService;

    @Autowired
    public AssociateController(AssociateServiceImpl associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid AssociateFormDTO associateFormDTO) {
        return this.associateService.create(associateFormDTO);
    }

    @PostMapping("/partidos")
    @Transactional
    public MessageResponseDTO link(@RequestBody @Valid LinkFormDTO linkFormDTO) {
        return this.associateService.link(linkFormDTO);
    }

    @GetMapping
    public List<AssociateDTO> find(@RequestParam(required = false) Position position, @RequestParam(required = false) String sort){
        return this.associateService.find(position, sort);
    }

    @GetMapping("/{id}")
    public AssociateDTO findById(@PathVariable Long id) {
        return this.associateService.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid AssociateFormDTO associateFormDTO) {
        return this.associateService.update(id, associateFormDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.associateService.delete(id);
    }

    @DeleteMapping("/{associateId}/partidos/{partyId}")
    @Transactional
    public MessageResponseDTO removeParty(@PathVariable Long associateId, @PathVariable Long partyId) {
        return this.associateService.removeParty(associateId, partyId);
    }
}

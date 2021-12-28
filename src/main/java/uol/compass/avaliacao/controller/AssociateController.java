package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.MessageResponseDTO;
import uol.compass.avaliacao.service.AssociateServiceImpl;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
}

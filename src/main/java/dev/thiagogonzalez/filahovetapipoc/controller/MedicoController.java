package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.MedicoDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Medico;
import dev.thiagogonzalez.filahovetapipoc.service.MedicoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


    @GetMapping
    public List<MedicoDTO> findAll() {
        return medicoService.findAll().stream().map(MedicoDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Medico> save(@RequestBody Medico medico) {
        Medico medicoCriado = medicoService.save(medico);
        return new ResponseEntity<>(medicoCriado, HttpStatus.CREATED);
    }

    @PutMapping("{idMedico}")
    public Medico update(@PathVariable("idMedico") Long idMedico, @RequestBody Medico dadosNovosMedico) {
        Medico medicoToUpdate = medicoService.findById(idMedico);

        BeanUtils.copyProperties(dadosNovosMedico, medicoToUpdate, "id");
        return medicoService.save(medicoToUpdate);
    }

    @DeleteMapping("{idMedico}")
    private Boolean delete(@PathVariable("idMedico") Long idMedico) {
        return medicoService.delete(idMedico);
    }

}

package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.SalaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import dev.thiagogonzalez.filahovetapipoc.service.SalaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sala")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }


    @GetMapping
    public List<SalaDTO> findAll() {
        return salaService.findAll().stream().map(SalaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{idSala}")
    public SalaDTO findById(@PathVariable("idSala") Long idSala) {
       return new SalaDTO(salaService.findById(idSala));
    }

    @PostMapping
    public ResponseEntity<Sala> save(@RequestBody Sala sala) {
        Sala salaCriada = salaService.save(sala);
        return new ResponseEntity<>(salaCriada, HttpStatus.CREATED);
    }

    @PutMapping("{idSala}")
    public Sala update(@PathVariable("idSala") Long idSala,@RequestBody Sala dadosNovosSala) {
        Sala salaToUpdate = salaService.findById(idSala);

        BeanUtils.copyProperties(dadosNovosSala, salaToUpdate, "id");
        return salaService.save(salaToUpdate);
    }

    @DeleteMapping("{idSala}")
    private Boolean delete(@PathVariable("idSala") Long idSala) {
        return salaService.delete(idSala);
    }

}

package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.SenhaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.SenhaEdicaoDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.service.SenhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/senha")
public class SenhaController {

    private final SenhaService senhaService;

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @GetMapping
    public List<SenhaDTO> findAll() {
        return senhaService.findAll().stream().map(SenhaDTO::new).collect(Collectors.toList());
    }

    @GetMapping("{idSenha}")
    public SenhaEdicaoDTO findById(@PathVariable("idSenha") Long idSenha) {
        return new SenhaEdicaoDTO(senhaService.findById(idSenha));
    }

    @PostMapping
    public Senha save(@RequestBody Senha senha) {
        return senhaService.save(senha);
    }

    @PutMapping("{idSenha}")
    public Senha update(@PathVariable("idSenha") Long idSenha, @RequestBody Senha dadosNovosSenha) {
        Senha senhaToUpdate = senhaService.findById(idSenha);

        BeanUtils.copyProperties(dadosNovosSenha, senhaToUpdate, "id");
        return senhaService.save(senhaToUpdate);
    }

    @DeleteMapping("{idSenha}")
    private Boolean delete(@PathVariable("idSenha") Long idSenha) {
        return senhaService.delete(idSenha);
    }

    @GetMapping("find-by-data")
    public List<SenhaDTO> findMedicalRecordsByDate(@RequestParam String data, @RequestParam Long filaId) {
        return senhaService
                .findMedicalRecordsByDate(data, filaId)
                .stream()
                .map(SenhaDTO::new)
                .toList();
    }
}

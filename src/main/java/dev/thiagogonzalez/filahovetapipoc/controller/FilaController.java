package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.FilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoFilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import dev.thiagogonzalez.filahovetapipoc.service.FilaService;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fila")
public class FilaController {
    private final SenhaRepository senhaRepository;
    private final FilaService filaService;
    private final SimpMessagingTemplate messagingTemplate;

    public FilaController(SenhaRepository senhaRepository, FilaService filaService, SimpMessagingTemplate messagingTemplate) {
        this.senhaRepository = senhaRepository;
        this.filaService = filaService;
        this.messagingTemplate = messagingTemplate;
    }


    @PostMapping("/chamar-proximo/{filaId}")
    public void chamarProximo(@PathVariable Long filaId) {
        Senha senhaAtual = senhaRepository.findSenhaAtualByIdFila(filaId);
        senhaAtual.setSituacao(SituacaoSenha.ATENDIDA);
        senhaRepository.save(senhaAtual);
        VisualizacaoFilaDTO visualizacaoFilaDTO = filaService.visualizarFila(filaId);
        messagingTemplate.convertAndSend("/topic/fila/1", visualizacaoFilaDTO);
    }




    @GetMapping("/visualizar/{filaId}")
    public VisualizacaoFilaDTO visualizarFila(@PathVariable Long filaId) {
        return filaService.visualizarFila(filaId);
    }

    @GetMapping
    public List<FilaDTO> findAll() {
        return filaService.findAll().stream().map(FilaDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public Fila save(@RequestBody Fila fila) {
        return filaService.save(fila);
    }

    @PutMapping("{idFila}")
    public Fila update(@PathVariable("idFila") Long idFila, @RequestBody Sala dadosNovosFila) {
        Fila salaToUpdate = filaService.findById(idFila);

        BeanUtils.copyProperties(dadosNovosFila, salaToUpdate, "id");
        return filaService.save(salaToUpdate);
    }

    @DeleteMapping("{idFila}")
    private Boolean delete(@PathVariable("idFila") Long idFila) {
        return filaService.delete(idFila);
    }

}

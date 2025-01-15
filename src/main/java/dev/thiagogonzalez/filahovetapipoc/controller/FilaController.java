package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoFilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import dev.thiagogonzalez.filahovetapipoc.service.FilaService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

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
}

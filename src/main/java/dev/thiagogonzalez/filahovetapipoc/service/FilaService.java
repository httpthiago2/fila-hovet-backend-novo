package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.SenhaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoFilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilaService {


    private final SenhaRepository senhaRepository;

    public FilaService(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    public VisualizacaoFilaDTO visualizarFila(Long id) {
        Senha senhaEntidade = senhaRepository.findSenhaAtualByIdFila(id);
        SenhaDTO senhaNova = new SenhaDTO(senhaEntidade);

        List<SenhaDTO> senhasAnteriores = senhaRepository.findSenhasAtendidasByIdFila(id)
                .stream()
                .map(SenhaDTO::new).toList();


        VisualizacaoFilaDTO visualizacaoFilaDTO = new VisualizacaoFilaDTO();
        visualizacaoFilaDTO.setSenhaAtual(senhaNova);
        visualizacaoFilaDTO.setNome(senhaEntidade.getFila().getNome());
        visualizacaoFilaDTO.setSala(senhaEntidade.getFila().getSala().getNome());
        visualizacaoFilaDTO.setSenhasAnteriores(senhasAnteriores);
        return visualizacaoFilaDTO;
    }
}

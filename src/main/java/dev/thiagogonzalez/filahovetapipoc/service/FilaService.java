package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoSenhaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoFilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.FilaRepository;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilaService {

    private final FilaRepository filaRepository;
    private final SenhaRepository senhaRepository;

    public FilaService(SenhaRepository senhaRepository, FilaRepository filaRepository) {
        this.senhaRepository = senhaRepository;
        this.filaRepository = filaRepository;
    }

    public VisualizacaoFilaDTO visualizarFila(Long id) {
        Senha senhaEntidade = senhaRepository.findSenhaAtualByIdFila(id);
        VisualizacaoSenhaDTO senhaNova = new VisualizacaoSenhaDTO(senhaEntidade);

        List<VisualizacaoSenhaDTO> senhasAnteriores = senhaRepository.findSenhasAtendidasByIdFila(id)
                .stream()
                .map(VisualizacaoSenhaDTO::new).toList();


        VisualizacaoFilaDTO visualizacaoFilaDTO = new VisualizacaoFilaDTO();
        visualizacaoFilaDTO.setSenhaAtual(senhaNova);
        visualizacaoFilaDTO.setNome(senhaEntidade.getFila().getNome());
        visualizacaoFilaDTO.setSala(senhaEntidade.getFila().getSala().getNome());
        visualizacaoFilaDTO.setSenhasAnteriores(senhasAnteriores);
        return visualizacaoFilaDTO;
    }

    public List<Fila> findAll() {
        return filaRepository.findAll();
    }

    public Fila findById(Long id) {
        return filaRepository.findById(id).orElse(null);
    }

    public Fila save(Fila fila) {
        return filaRepository.save(fila);
    }

    public Boolean delete(Long id) {

        try {
            filaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public List<Fila> findBySituacao(SituacaoFila situacaoFila) {
        return filaRepository.findBySituacao(situacaoFila);
    }


}

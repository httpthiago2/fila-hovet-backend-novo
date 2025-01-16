package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.SenhaDTO;
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
        SenhaDTO senhaNova = new SenhaDTO(senhaEntidade);

        List<SenhaDTO> senhasAnteriores = senhaRepository.findSenhasAtendidasByIdFila(id)
                .stream()
                .map(SenhaDTO::new)
                .toList();

        List<SenhaDTO> proximasSenhas = senhaRepository.findProximasSenhas(id, senhaEntidade.getId())
                .stream()
                .map(SenhaDTO::new)
                .toList();


        VisualizacaoFilaDTO visualizacaoFilaDTO = new VisualizacaoFilaDTO();

        // dados da fila
        visualizacaoFilaDTO.setNome(senhaEntidade.getFila().getNome());
        visualizacaoFilaDTO.setSala(senhaEntidade.getFila().getSala().getNome());

        // senha atual
        visualizacaoFilaDTO.setSenhaAtual(senhaNova);

        // senhas anteriores
        visualizacaoFilaDTO.setSenhasAnteriores(senhasAnteriores);

        // proximas senhas
        visualizacaoFilaDTO.setProximasSenhas(proximasSenhas);

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

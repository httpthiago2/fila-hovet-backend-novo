package dev.thiagogonzalez.filahovetapipoc.service;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.FilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.SenhaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoSenhaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.dto.VisualizacaoFilaDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.FilaRepository;
import dev.thiagogonzalez.filahovetapipoc.domain.repository.SenhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FilaService {

    private final FilaRepository filaRepository;
    private final SenhaRepository senhaRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public FilaService(SenhaRepository senhaRepository, FilaRepository filaRepository, SimpMessagingTemplate messagingTemplate) {
        this.senhaRepository = senhaRepository;
        this.filaRepository = filaRepository;
        this.messagingTemplate = messagingTemplate;
    }


    public VisualizacaoFilaDTO chamarProximo(Long filaId) throws Exception {

        // busca filas com o status EM_ATENDIMENTO
        Senha senhaAtual = senhaRepository.findSenhaAtualByIdFila(filaId);

        if (senhaAtual == null) {
            senhaAtual = senhaRepository.findProximaSenhaByIdFila(filaId);
            if (senhaAtual == null) {
                return visualizarFila(filaId);
            }
            senhaAtual.setSituacao(SituacaoSenha.EM_ATENDIMENTO);
            senhaRepository.save(senhaAtual);
            return visualizarFila(filaId);
        } else {
            senhaAtual.setSituacao(SituacaoSenha.ATENDIDA);
            senhaRepository.save(senhaAtual);

            senhaAtual = senhaRepository.findProximaSenhaByIdFila(filaId);
            senhaAtual.setSituacao(SituacaoSenha.EM_ATENDIMENTO);
            senhaRepository.save(senhaAtual);

            return visualizarFila(filaId);
        }
    }

    public VisualizacaoFilaDTO visualizarFila(Long id) throws Exception {
        Senha senhaEntidade = senhaRepository.findSenhaAtualByIdFila(id);
        Fila fila = filaRepository.findById(id).orElseThrow(() -> new Exception("Fila nao encontrada"));
        SenhaDTO senhaNova = null;

        if (senhaEntidade != null) {
            senhaNova = new SenhaDTO(senhaEntidade);
        }


        List<SenhaDTO> senhasAnteriores = senhaRepository.findSenhasAtendidasByIdFila(id)
                .stream()
                .map(SenhaDTO::new)
                .toList();

        List<SenhaDTO> proximasSenhas = senhaRepository.findProximasSenhas(id)
                .stream()
                .map(SenhaDTO::new)
                .toList();




        VisualizacaoFilaDTO visualizacaoFilaDTO = new VisualizacaoFilaDTO();

        // dados da fila
        visualizacaoFilaDTO.setNome(fila.getNome());
        visualizacaoFilaDTO.setSala(fila.getSala().getNome());

        // senha atual
        visualizacaoFilaDTO.setSenhaAtual(senhaNova);

        // senhas anteriores
        visualizacaoFilaDTO.setSenhasAnteriores(senhasAnteriores);

        // proximas senhas
        visualizacaoFilaDTO.setProximasSenhas(proximasSenhas);

        return visualizacaoFilaDTO;
    }

    public List<VisualizacaoFilaDTO> visualizarTodasFilas() throws Exception {

        List<Fila> filas = filaRepository.findAll();
        List<VisualizacaoFilaDTO> retorno = new ArrayList<>();


        for (Fila fila : filas) {
            retorno.add(visualizarFila(fila.getId()));
        }

        return retorno;
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

package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VisualizacaoFilaDTO {
    private String nome;
    private String sala;
    private VisualizacaoSenhaDTO senhaAtual;
    private List<VisualizacaoSenhaDTO> senhasAnteriores;
}

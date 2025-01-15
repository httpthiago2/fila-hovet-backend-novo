package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import lombok.Data;

@Data
public class FilaDTO {
    private Long id;
    private String codigo;
    private String nome;
    private SituacaoFila situacao;
    private String nomeMedico;
    private String nomeSala;

    public FilaDTO(Fila fila) {
        this.id = fila.getId();
        this.codigo = fila.getCodigo();
        this.nome = fila.getNome();
        this.situacao = fila.getSituacao();
        this.nomeMedico = fila.getUsuario().getNome();
        this.nomeSala = fila.getSala().getNome();
    }
}

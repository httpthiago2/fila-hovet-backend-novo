package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import lombok.Data;

@Data
public class FilaDTO {
    private Long id;
    private String codigo;
    private String nome;
    private SituacaoFila situacao;
    private UsuarioDTO usuario;
    private SalaDTO sala;


    public FilaDTO(Fila fila) {
        this.id = fila.getId();
        this.codigo = fila.getCodigo();
        this.nome = fila.getNome();
        this.situacao = fila.getSituacao();
        this.sala = new SalaDTO(fila.getSala());
        this.usuario = new UsuarioDTO(fila.getUsuario());
    }
}

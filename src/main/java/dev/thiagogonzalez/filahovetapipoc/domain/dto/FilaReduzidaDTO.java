package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import lombok.Data;

@Data
public class FilaReduzidaDTO {
    private Long id;
    private String nome;

    public FilaReduzidaDTO(Fila fila) {
        this.id = fila.getId();
        this.nome = fila.getNome();
    }
}

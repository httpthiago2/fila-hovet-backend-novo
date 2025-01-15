package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import lombok.Data;

@Data
public class SalaDTO {
    private Long id;
    private String nome;

    public SalaDTO(Sala sala) {
        this.id = sala.getId();
        this.nome = sala.getNome();
    }
}

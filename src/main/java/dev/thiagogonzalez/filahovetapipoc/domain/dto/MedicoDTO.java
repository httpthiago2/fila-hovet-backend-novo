package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Medico;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import lombok.Data;

@Data
public class MedicoDTO {
    private Long id;
    private String nome;
    private String usuario;

    public MedicoDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.usuario = medico.getUsuario();
    }
}

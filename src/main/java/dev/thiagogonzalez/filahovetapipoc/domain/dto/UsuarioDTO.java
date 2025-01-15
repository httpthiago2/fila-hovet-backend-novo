package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoPerfil;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String usuario;
    private TipoPerfil perfil;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.usuario = usuario.getUsuario();
        this.perfil = usuario.getPerfil();
    }
}

package dev.thiagogonzalez.filahovetapipoc.domain.repository;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoPerfil;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findByPerfil(TipoPerfil perfil);
}

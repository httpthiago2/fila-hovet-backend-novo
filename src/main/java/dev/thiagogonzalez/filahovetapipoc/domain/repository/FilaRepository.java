package dev.thiagogonzalez.filahovetapipoc.domain.repository;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long>{
    public List<Fila> findBySituacao(SituacaoFila situacaoFila);
}

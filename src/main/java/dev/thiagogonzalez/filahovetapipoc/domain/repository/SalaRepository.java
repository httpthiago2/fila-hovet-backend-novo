package dev.thiagogonzalez.filahovetapipoc.domain.repository;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}

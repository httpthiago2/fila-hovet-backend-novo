package dev.thiagogonzalez.filahovetapipoc.domain.repository;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {
    @Query("SELECT s FROM Senha s WHERE s.situacao = 'PENDENTE_ATENDIMENTO' AND s.fila.id = 1 order by s.ordem limit 1")
    public Senha findSenhaAtualByIdFila(Long idFila);

    @Query("SELECT s FROM Senha s WHERE s.situacao = 'ATENDIDA' AND s.fila.id = 1 order by s.ordem DESC")
    public List<Senha> findSenhasAtendidasByIdFila(Long filaId);

    @Query("SELECT s FROM Senha s WHERE DATE(s.dataCriacao) = DATE(:date) AND s.fila.id = :filaId")
    public List<Senha> queryFindByDate(@Param("date") String date, @Param("filaId") Long filaId);
}

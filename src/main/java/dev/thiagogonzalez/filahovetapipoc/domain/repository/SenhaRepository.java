package dev.thiagogonzalez.filahovetapipoc.domain.repository;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {
    @Query("SELECT s FROM Senha s WHERE s.situacao = 'EM_ATENDIMENTO' AND s.fila.id = :idFila")
    public Senha findSenhaAtualByIdFila(@Param("idFila") Long idFila);

    @Query("SELECT s FROM Senha s WHERE s.situacao = 'PENDENTE_ATENDIMENTO' AND s.fila.id = :idFila order by s.dataCriacao limit 1")
    public Senha findProximaSenhaByIdFila(@Param("idFila") Long idFila);

    @Query("SELECT s FROM Senha s WHERE s.situacao = 'PENDENTE_ATENDIMENTO' AND s.fila.id = :idFila order by s.dataCriacao")
    public List<Senha> findProximasSenhas(@Param("idFila") Long idFila);

    @Query("SELECT s FROM Senha s WHERE s.situacao = 'ATENDIDA' AND s.fila.id = :idFila order by s.ordem DESC LIMIT 3")
    public List<Senha> findSenhasAtendidasByIdFila(@Param("idFila") Long idFila);

    @Query("SELECT s FROM Senha s WHERE DATE(s.dataCriacao) = DATE(:date) AND s.fila.id = :filaId")
    public List<Senha> queryFindByDate(@Param("date") String date, @Param("filaId") Long filaId);
}

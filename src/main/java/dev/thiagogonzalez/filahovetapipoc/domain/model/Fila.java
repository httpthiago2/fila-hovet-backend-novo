package dev.thiagogonzalez.filahovetapipoc.domain.model;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoFila;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Fila {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private SituacaoFila situacao;

    @OneToMany(mappedBy = "fila", fetch = FetchType.LAZY)
    private List<Senha> senhas;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}

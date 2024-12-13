package dev.thiagogonzalez.filahovetapipoc.domain.model;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoSenha;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Senha {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ordem;

    @Enumerated(EnumType.STRING)
    private TipoSenha tipo;

    @Enumerated(EnumType.STRING)
    private SituacaoSenha situacao;

    private String pet;

    private String tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fila_id", nullable = false)
    private Fila fila;
}

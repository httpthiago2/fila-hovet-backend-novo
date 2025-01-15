package dev.thiagogonzalez.filahovetapipoc.domain.model;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoPerfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String usuario;

    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoPerfil perfil;

    @OneToMany(mappedBy = "usuario")
    private List<Fila> filas = new ArrayList<>();
}

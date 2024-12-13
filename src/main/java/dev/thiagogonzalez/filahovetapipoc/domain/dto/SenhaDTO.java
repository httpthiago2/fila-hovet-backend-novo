package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class SenhaDTO {
    private String senha;
    private Long ordem;
    private String pet;
    private String tutor;


    public SenhaDTO(Senha senha) {
        this.senha = senha.getFila().getCodigo().concat(StringUtils.leftPad(senha.getId().toString(), 3, '0'));
        this.ordem = senha.getOrdem();
        this.pet = senha.getPet();
        this.tutor = senha.getTutor();
    }
}

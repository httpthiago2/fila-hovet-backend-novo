package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class SenhaEdicaoDTO {
    private Long id;
    private String codigo;
    private String nomePet;
    private String nomeTutor;
    private String nomeFila;
    private TipoSenha tipoSenha;
    private SituacaoSenha situacao;
    private Long ordem;
    private FilaReduzidaDTO fila;

    public SenhaEdicaoDTO(Senha senha) {
        this.id = senha.getId();
        this.codigo = senha.getFila().getCodigo().concat(StringUtils.leftPad(senha.getId().toString(), 3, '0'));
        this.nomePet = senha.getPet();
        this.nomeTutor = senha.getTutor();
        this.nomeFila = senha.getFila().getNome();
        this.tipoSenha = senha.getTipo();
        this.situacao = senha.getSituacao();
        this.fila = new FilaReduzidaDTO(senha.getFila());
        this.ordem = senha.getOrdem();
    }
}

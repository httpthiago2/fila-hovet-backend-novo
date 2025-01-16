package dev.thiagogonzalez.filahovetapipoc.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.SituacaoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoSenha;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Senha;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Data
public class SenhaDTO {
    private String codigo;
    private String nomePet;
    private String nomeTutor;
    private String nomeFila;
    private TipoSenha tipoSenha;
    private SituacaoSenha situacao;

    @JsonFormat(pattern = "dd/MM/yyyy hh:MM:ss")
    private LocalDateTime dataCriacao;

    public SenhaDTO(Senha senha) {
        this.codigo = senha.getFila().getCodigo().concat(StringUtils.leftPad(senha.getId().toString(), 3, '0'));
        this.nomePet = senha.getPet();
        this.nomeTutor = senha.getTutor();
        this.nomeFila = senha.getFila().getNome();
        this.tipoSenha = senha.getTipo();
        this.situacao = senha.getSituacao();
        this.dataCriacao = senha.getDataCriacao();
    }
}

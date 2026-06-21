package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.TipoOperacao;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class AlteracaoPermissao {
    private Usuario usuario;
    private LocalDateTime dataAlteracao;
    private TipoOperacao tipoOperacao;

    private String nomePPC;

    public AlteracaoPermissao(Usuario usuario, LocalDateTime dataAlteracao, TipoOperacao tipoOperacao, String nomePPC) {
        this.usuario = usuario;
        this.dataAlteracao = dataAlteracao;
        this.tipoOperacao = tipoOperacao;
        this.nomePPC = nomePPC;
    }
}
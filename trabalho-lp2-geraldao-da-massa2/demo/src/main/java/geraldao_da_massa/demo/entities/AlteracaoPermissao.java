package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AlterarPermissao")

@Getter
@Setter
public class AlteracaoPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPermissao;
    @OneToOne
    private Usuario usuario;
    private LocalDateTime dataAlteracao;
    private TipoOperacao tipoOperacao;
    private String nomePPC;

    public AlteracaoPermissao(Usuario usuario, LocalDateTime dataAlteracao, TipoOperacao tipoOperacao,  String nomePPC) {
        this.usuario = usuario;
        this.dataAlteracao = dataAlteracao;
        this.tipoOperacao = tipoOperacao;
        this.nomePPC = nomePPC;
    }
}

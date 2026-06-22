package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusSolicitacaoOportunidade;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class SolicitacaoOportunidade {
    private Discente discenteSolicitante;
    private Oportunidade oportunidadeRequerida;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAprovacao;
    private LocalDateTime dataIndeferimento;
    private StatusSolicitacaoOportunidade status;

    public SolicitacaoOportunidade(Discente discenteSolicitante, Oportunidade oportunidadeRequerida) {
        this.discenteSolicitante = discenteSolicitante;
        this.oportunidadeRequerida = oportunidadeRequerida;
        this.dataSolicitacao = LocalDateTime.now();
        this.dataAprovacao = null;
        this.dataIndeferimento = null;
        this.status = StatusSolicitacaoOportunidade.PENDENTE;
    }
}

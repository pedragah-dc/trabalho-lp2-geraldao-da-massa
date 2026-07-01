<<<<<<< HEAD:trabalho-lp2-geraldao-da-massa2/demo/src/main/java/geraldao_da_massa/demo/entities/SolicitacaoOportunidade.java
package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusSolicitacaoOportunidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
//nao sei como funciona isso
@NoArgsConstructor
@Entity
@Table(name = "solicitacao_oportunidade")
@Getter
@Setter
public class SolicitacaoOportunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSolicitacaoOportunidade;
    @OneToOne
    private Discente discenteSolicitante;
    @OneToOne
=======
package entity;

import entity.enums.StatusSolicitacaoOportunidade;

import java.time.LocalDateTime;

public class SolicitacaoOportunidade {
    private Discente discenteSolicitante;
>>>>>>> ad2863fb1d92ebc72fd1724ecc049d531ba9e063:src/entity/SolicitacaoOportunidade.java
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

    public Discente getDiscenteSolicitante() {
        return discenteSolicitante;
    }

    public void setDiscenteSolicitante(Discente discenteSolicitante) {
        this.discenteSolicitante = discenteSolicitante;
    }

    public Oportunidade getOportunidadeRequerida() {
        return oportunidadeRequerida;
    }

    public void setOportunidadeRequerida(Oportunidade oportunidadeRequerida) {
        this.oportunidadeRequerida = oportunidadeRequerida;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusSolicitacaoOportunidade getStatus() {
        return status;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public LocalDateTime getDataIndeferimento() {
        return dataIndeferimento;
    }

    public void setDataIndeferimento(LocalDateTime dataIndeferimento) {
        this.dataIndeferimento = dataIndeferimento;
    }

    public void setStatus(StatusSolicitacaoOportunidade status) {
        this.status = status;
    }
}

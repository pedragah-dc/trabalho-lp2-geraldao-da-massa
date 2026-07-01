package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.SolicitacaoOportunidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoOportunidadeResponseDTO {
    private String nomeDiscente;
    private String tituloOportunidade;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAprovacao;
    private LocalDateTime dataIndeferimento;
    private String status;


    SolicitacaoOportunidadeResponseDTO(SolicitacaoOportunidade solOp) {
        this.nomeDiscente = solOp.getDiscenteSolicitante().getNome();
        this.tituloOportunidade = solOp.getOportunidadeRequerida().getTitulo();
        this.dataSolicitacao = solOp.getDataSolicitacao();
        this.dataAprovacao = solOp.getDataAprovacao();
        this.dataIndeferimento = solOp.getDataIndeferimento();
        this.status = solOp.getStatus().name();
    }
}

package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Aproveitamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AproveitamentoResponseDTO {
    private String discente;
    private String descricao;
    private String instituicao;
    private Integer horas;
    private String status;


    public AproveitamentoResponseDTO(Aproveitamento apro){
    this.discente = apro.getDiscente().getNome();
    this.descricao = apro.getDescricao();
    this.instituicao = apro.getInstituicao();
    this.horas = apro.getHoras();
    this.status = apro.getStatus().name();
    }
}

package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoResponseDTO {

    private Integer idInscricao;
    private String discente;
    private String oportunidade;
    private String status;
    private String motivacao;

    public InscricaoResponseDTO(Inscricao inscricao) {
        this.idInscricao = inscricao.getIdInscricao();
        this.discente = inscricao.getDiscente().getNome();
        this.oportunidade = inscricao.getOportunidade().getTitulo();
        this.status = inscricao.getStatus().name();
        this.motivacao = inscricao.getMotivacao();
    }
}

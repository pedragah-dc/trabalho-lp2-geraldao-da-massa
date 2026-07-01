package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoResponseDTO {
    private String tituloOportunidade;
    private String nomeDiscente;
    private String status;
    private String motivacao;


    InscricaoResponseDTO(Inscricao inscricao) {
        this.tituloOportunidade = inscricao.getOportunidade().getTitulo();
        this.nomeDiscente = inscricao.getDiscente().getNome();
        this.status = inscricao.getStatus().name();
        this.motivacao = inscricao.getMotivacao();

    }
}

package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoResponseDTO {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private String status;
    private String responsavel;


    GrupoResponseDTO(Grupo grupo){
        this.nome = grupo.getNome();
        this.tipo = grupo.getTipo();
        this.email = grupo.getEmail();
        this.descricao = grupo.getDescricao();
        this.status = grupo.getStatus().name();
        this.responsavel = grupo.getResponsavel().getNome();
    }
}

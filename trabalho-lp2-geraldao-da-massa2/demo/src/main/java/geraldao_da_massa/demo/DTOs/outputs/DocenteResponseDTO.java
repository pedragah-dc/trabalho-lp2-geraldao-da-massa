package geraldao_da_massa.demo.DTOs.outputs;

import geraldao_da_massa.demo.entities.Docente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteResponseDTO {
    private Integer id;
    private String nome;
    private String email;
    private Boolean ativo;
    private String descricao;
    private String role;
    private String siape;
    private String departamento;

    DocenteResponseDTO(Docente docente){
        this.id = docente.getId();
        this.nome = docente.getNome();
        this.email = docente.getEmail();
        this.ativo = docente.getAtivo();
        this.descricao = docente.getPapel().getDescricao();
        this.role = docente.getRole().name();
        this.siape = docente.getSiape();
        this.departamento = docente.getDepartamento();
    }
}

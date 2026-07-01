package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoRequestDTO {
    @NotBlank(message="O nome do grupo é obrigatório.")
    private String nome;

    @NotBlank(message="O tipo do grupo é obrigatório.")
    private String tipo;

    @NotBlank(message="O email do grupo é obrigatório.")
    private String email;

    @NotBlank(message="A descrição do grupo é obrigatória.")
    private String descricao;

    @NotNull(message="O ID do responsável pelo grupo é obrigatório.")
    private Integer idResponsavel;

}

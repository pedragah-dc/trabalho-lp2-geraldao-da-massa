package geraldao_da_massa.demo.DTOs.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoRequestDTO {
    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;

    @Positive(message = "O código do curso deve ser um valor positivo.")
    private Integer codigo;

    @Positive(message="A carga Horaria do curso deve ser um valor positivo.")
    private Integer cargaHoraria;

    @NotBlank(message="A versão do PPC é obrigatória.")
    private String versaoPPC;


}

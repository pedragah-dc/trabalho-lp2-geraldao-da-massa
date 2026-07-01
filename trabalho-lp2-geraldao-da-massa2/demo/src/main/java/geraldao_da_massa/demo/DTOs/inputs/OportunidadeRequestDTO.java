package geraldao_da_massa.demo.DTOs.inputs;

import com.fasterxml.jackson.annotation.JsonFormat;
import geraldao_da_massa.demo.entities.enums.TiposModalidade;
import geraldao_da_massa.demo.entities.enums.TiposOportunidade;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OportunidadeRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O tipo da oportunidade é obrigatório.")
    private TiposOportunidade tipo;

    @NotNull(message = "A modalidade é obrigatória.")
    private TiposModalidade modalidade;

    @NotNull(message = "A carga horária é obrigatória.")
    @Positive(message = "A carga horária deve ser maior que zero.")
    private Integer cargaHoraria;

    @NotNull(message = "O número de vagas é obrigatório.")
    @Positive(message = "O número de vagas deve ser maior que zero.")
    private Integer vagas;

    @NotNull(message = "A data de início é obrigatória.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inicio;

    @NotNull(message = "A data de fim é obrigatória.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fim;

    @NotNull(message = "A data de início das inscrições é obrigatória.")
    @FutureOrPresent(message = "A data de início das inscrições não pode ser no passado.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicioInscricoes;

    @NotNull(message = "A data de fim das inscrições é obrigatória.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFimInscricoes;

    @NotNull(message = "O ID do autor é obrigatório.")
    private Integer autorId;

    @NotNull(message = "O ID do docente responsável é obrigatório.")
    private Integer docenteResponsavelId;
}
package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Usuario;
import geraldao_da_massa.demo.entities.enums.TiposModalidade;
import geraldao_da_massa.demo.entities.enums.TiposOportunidade;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@NotNull
//deixa a responsabilidade de procurar classes na camada service?
public class OportunidadeRequestDTO {
    private String titulo;
    private String descricao;
    private TiposOportunidade tipo;
    private TiposModalidade modalidade;
    private Integer cargaHoraria;
    private Integer vagas;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private LocalDateTime dataInicioInscricoes;
    private LocalDateTime dataFimInscricoes;
    private Usuario autor;
    private Docente docenteResponsavel;


}

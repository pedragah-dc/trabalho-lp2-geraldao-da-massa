package geraldao_da_massa.demo.DTOs;

import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Usuario;
import geraldao_da_massa.demo.entities.enums.TiposModalidade;
import geraldao_da_massa.demo.entities.enums.TiposOportunidade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@NotNull
//deixa a responsabilidade de procurar classes na camada service?
public class OportunidadeRequestDTO {
    public String titulo;
    public String descricao;
    public TiposOportunidade tipo;
    public TiposModalidade modalidade;
    public Integer cargaHoraria;
    public Integer vagas;
    public LocalDateTime inicio;
    public LocalDateTime fim;
    public LocalDateTime dataInicioInscricoes;
    public LocalDateTime dataFimInscricoes;
    public Usuario autor;
    public Docente docenteResponsavel;


}

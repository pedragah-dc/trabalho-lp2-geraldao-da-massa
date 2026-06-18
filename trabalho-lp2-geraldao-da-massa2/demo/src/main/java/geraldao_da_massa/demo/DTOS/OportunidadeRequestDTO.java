package geraldao_da_massa.demo.DTOS;

import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.Usuario;
import geraldao_da_massa.demo.entity.enums.TiposModalidade;
import geraldao_da_massa.demo.entity.enums.TiposOportunidade;

import java.time.LocalDateTime;

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

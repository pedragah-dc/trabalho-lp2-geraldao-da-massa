package geraldao_da_massa.demo.DTOs.outputs;


import geraldao_da_massa.demo.entities.Oportunidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OportunidadeResponseDTO {

    private String titulo;
    private String descricao;
    private String tipo;
    private String modalidade;
    private Integer cargaHoraria;
    private Integer vagas;
    private String status;

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private LocalDateTime dataInicioInscricoes;
    private LocalDateTime dataFimInscricoes;

    private String autor;
    private String docenteResponsavel;
    private String feedback;


    public OportunidadeResponseDTO(Oportunidade oportunidade){
     this.titulo = oportunidade.getTitulo();
     this.descricao = oportunidade.getDescricao();

     this.tipo = oportunidade.getTipo().name();
     this.modalidade = oportunidade.getModalidade().name();
     this.cargaHoraria = oportunidade.getCargaHoraria();
     this.vagas = oportunidade.getVagas();
     this.status = oportunidade.getStatus().name();
     this.inicio = oportunidade.getInicio();
     this.fim = oportunidade.getFim();
     this.dataInicioInscricoes = oportunidade.getDataInicioInscricoes();
     this.dataFimInscricoes = oportunidade.getDataFimInscricoes();
     this.autor = oportunidade.getAutor().getNome();
     this.docenteResponsavel = oportunidade.getDocenteResponsavel().getNome();
     this.feedback = oportunidade.getFeedbackReprovacao();
    }
}

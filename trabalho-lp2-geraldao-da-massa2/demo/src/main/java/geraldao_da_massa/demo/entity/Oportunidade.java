package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.StatusOportunidade;
import geraldao_da_massa.demo.entity.enums.TiposModalidade;
import geraldao_da_massa.demo.entity.enums.TiposOportunidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class Oportunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String descricao;
    private TiposOportunidade tipo;
    private TiposModalidade modalidade;
    private Integer cargaHoraria;
    private Integer vagas;
    private StatusOportunidade status;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private LocalDateTime dataInicioInscricoes; // quando abre inscrições
    private LocalDateTime dataFimInscricoes;// quando fecha inscrições
    @OneToOne
    private Usuario autor;                      // quem criou (discente ou docente)
    @OneToOne
    private Docente docenteResponsavel;         // docente que aprova
    private String feedbackReprovacao;          // motivo caso reprovada

    // Construtor usado ao CRIAR uma oportunidade nova
    // Repare: o status começa sempre como RASCUNHO, não passamos ele aqui
    public Oportunidade(String titulo, String descricao, TiposOportunidade tipo,
                        TiposModalidade modalidade, Integer cargaHoraria, Integer vagas,
                        LocalDateTime inicio, LocalDateTime fim,
                        LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
                        Usuario autor, Docente docenteResponsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.inicio = inicio;
        this.fim = fim;
        this.dataInicioInscricoes = dataInicioInscricoes;
        this.dataFimInscricoes = dataFimInscricoes;
        this.autor = autor;
        this.docenteResponsavel = docenteResponsavel;
        this.status = StatusOportunidade.RASCUNHO; // sempre começa como rascunho
    }

    public boolean isInscricoesAbertas() {
        LocalDateTime agora = LocalDateTime.now();
        return status == StatusOportunidade.EM_INSCRICOES
                && agora.isAfter(dataInicioInscricoes)
                && agora.isBefore(dataFimInscricoes);
    }

    public boolean isFinalizada() {
        return LocalDateTime.now().isAfter(this.fim);
    }

    // --- Getters e Setters ---

    @Override
    public String toString() {
        return "Oportunidade{titulo='" + titulo + "', status=" + status + ", vagas=" + vagas + "}";
    }
}

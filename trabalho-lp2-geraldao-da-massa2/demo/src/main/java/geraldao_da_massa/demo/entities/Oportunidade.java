package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.StatusOportunidade;
import geraldao_da_massa.demo.entities.enums.TiposModalidade;
import geraldao_da_massa.demo.entities.enums.TiposOportunidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Oportunidades")
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

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public TiposOportunidade getTipo() { return tipo; }
    public void setTipo(TiposOportunidade tipo) { this.tipo = tipo; }

    public TiposModalidade getModalidade() { return modalidade; }
    public void setModalidade(TiposModalidade modalidade) { this.modalidade = modalidade; }

    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public Integer getVagas() { return vagas; }
    public void setVagas(Integer vagas) { this.vagas = vagas; }

    public StatusOportunidade getStatus() { return status; }
    public void setStatus(StatusOportunidade status) { this.status = status; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFim() { return fim; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    public LocalDateTime getDataInicioInscricoes() { return dataInicioInscricoes; }
    public void setDataInicioInscricoes(LocalDateTime dataInicioInscricoes) { this.dataInicioInscricoes = dataInicioInscricoes; }

    public LocalDateTime getDataFimInscricoes() { return dataFimInscricoes; }
    public void setDataFimInscricoes(LocalDateTime dataFimInscricoes) { this.dataFimInscricoes = dataFimInscricoes; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    public Docente getDocenteResponsavel() { return docenteResponsavel; }
    public void setDocenteResponsavel(Docente docenteResponsavel) { this.docenteResponsavel = docenteResponsavel; }

    public String getFeedbackReprovacao() { return feedbackReprovacao; }
    public void setFeedbackReprovacao(String feedbackReprovacao) { this.feedbackReprovacao = feedbackReprovacao; }

    @Override
    public String toString() {
        return "Oportunidade{titulo='" + titulo + "', status=" + status + ", vagas=" + vagas + "}";
    }
}

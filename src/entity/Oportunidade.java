package entity;

import entity.enums.StatusOportunidade;
import entity.enums.TiposOportunidade;
import entity.enums.TiposModalidade;

import java.time.LocalDateTime;
import java.util.List;

public class Oportunidade {
    private String titulo;
    private String descricao;
    private Enum<TiposOportunidade> tipo;
    private Enum<TiposModalidade> modalidade;
    private Integer cargaHoraria;
    private Integer vagas;
    private Enum<StatusOportunidade> statusOportunidade;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Usuario autor;
    private Docente docente;

    public Oportunidade(String titulo, String descricao, Enum<TiposOportunidade> tipo, Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas, Enum<StatusOportunidade> statusOportunidade, LocalDateTime inicio, LocalDateTime fim, Usuario autor, Docente docente) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.statusOportunidade = statusOportunidade;
        this.inicio = inicio;
        this.fim = fim;
        this.autor = autor;
        this.docente = docente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Enum<TiposOportunidade> getTipo() {
        return tipo;
    }

    public void setTipo(Enum<TiposOportunidade> tipo) {
        this.tipo = tipo;
    }

    public Enum<TiposModalidade> getModalidade() {
        return modalidade;
    }

    public void setModalidade(Enum<TiposModalidade> modalidade) {
        this.modalidade = modalidade;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Enum<StatusOportunidade> getStatusOportunidade() {
        return statusOportunidade;
    }

    public void setStatusOportunidade(Enum<StatusOportunidade> statusOportunidade) {
        this.statusOportunidade = statusOportunidade;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public Boolean isFinalizada(){
        return LocalDateTime.now().isAfter(this.fim);
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}

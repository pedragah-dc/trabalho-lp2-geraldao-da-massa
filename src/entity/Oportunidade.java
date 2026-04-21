package entity;

import entity.enums.StatusOportunidade;
import entity.enums.TiposOportunidade;
import entity.enums.TiposModalidade;

import java.util.Date;

public class Oportunidade {
    String titulo;
    String descricao;
    Enum<TiposOportunidade> tipo;
    Enum<TiposModalidade> modalidade;
    Integer cargaHoraria;
    Integer vagas;
    Enum<StatusOportunidade> statusOportunidade;
    Date inicio;
    Date fim;
    Usuario autor;
    Docente docente;

    public Oportunidade(String titulo, String descricao, Enum<TiposOportunidade> tipo, Enum<TiposModalidade> modalidade, Integer cargaHoraria, Integer vagas, Enum<StatusOportunidade> statusOportunidade, Date inicio, Date fim, Usuario autor, Docente docente) {
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
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

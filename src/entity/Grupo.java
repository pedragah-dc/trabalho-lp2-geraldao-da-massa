package entity;

import entity.enums.StatusGrupo;

import java.util.ArrayList;

public class Grupo {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private StatusGrupo status; // corrigido: era Enum<StatusGrupo>
    private Docente responsavel;
    private ArrayList<MembroGrupo> membros;

    public Grupo(String nome, String tipo, String email, String descricao,
                 StatusGrupo status, Docente responsavel) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.status = status;
        this.responsavel = responsavel;
        membros = new ArrayList<MembroGrupo>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmail() { return email; }

    public void setStatus(StatusGrupo status) { this.status = status; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Enum<StatusGrupo> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusGrupo> status) {
        this.status = status;
    }

    public Docente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }

    public void adicionarMembro(MembroGrupo membro){

        if(membro != null) membros.add(membro);
    }
}

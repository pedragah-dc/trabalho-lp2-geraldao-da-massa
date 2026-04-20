package entitades;

import entitades.enums.StatusGrupo;

public class Grupo {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private Enum<StatusGrupo> status;

    public Grupo(String nome, String tipo, String email, String descricao, Enum<StatusGrupo> status) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

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
}

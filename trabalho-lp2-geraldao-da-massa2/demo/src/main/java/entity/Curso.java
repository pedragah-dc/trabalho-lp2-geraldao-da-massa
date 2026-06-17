package entity;

import java.util.List;

public class Curso {
    private String nome;
    private Integer codigo;
    private Integer cargaHoraria;
    private String versaoPPC;
    private List<AlteracaoPermissao> listaAlteracaoPPC;

    public Curso(String nome, Integer codigo, Integer cargaHoraria, String versaoPPC) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.versaoPPC = versaoPPC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getVersaoPPC() {
        return versaoPPC;
    }

    public void setVersaoPPC(String versaoPPC) {
        this.versaoPPC = versaoPPC;
    }

    public List<AlteracaoPermissao> getListaAlteracaoPPC() {
        return listaAlteracaoPPC;
    }
    public void setListaAlteracaoPPC(List<AlteracaoPermissao> listaAlteracaoPPC) {
        this.listaAlteracaoPPC = listaAlteracaoPPC;
    }
}

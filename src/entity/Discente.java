package entity;

import entity.enums.RolesUsuario;

import java.util.List;

public class Discente extends Usuario {
    private String matricula;
    private Integer semestreAtual;
    private Curso curso;

    public Discente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, Enum<RolesUsuario> role, List<Oportunidade> listaOp, String matricula, Integer semestreAtual, Curso curso) {
        super(id, nome, email, senha, papel, ativo, role, listaOp);
        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getSemestreAtual() {
        return semestreAtual;
    }

    public void setSemestreAtual(Integer semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}

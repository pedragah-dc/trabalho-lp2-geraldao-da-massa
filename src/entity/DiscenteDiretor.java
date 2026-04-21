package entity;

import java.util.Date;

public class DiscenteDiretor extends Discente {
    private Grupo grupo;
    private String cargo;
    private Date dataInicio;
    private Date dataFim;

    public DiscenteDiretor(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, String matricula, Integer semestreAtual, Curso curso, Grupo grupo, String cargo, Date dataInicio, Date dataFim) {
        super(id, nome, email, senha, papel, ativo, matricula, semestreAtual, curso);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}

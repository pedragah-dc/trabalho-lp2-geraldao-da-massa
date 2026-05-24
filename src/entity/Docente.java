package entity;

import java.util.HashSet;

public class Docente extends Usuario{
    private String siape;
    private String departamento;
    private HashSet<Grupo> grupos;


    public Docente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, String siape, String departamento) {
        super(id, nome, email, senha, papel, ativo);
        this.siape = siape;
        this.departamento = departamento;
        grupos = new HashSet<Grupo>();
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

package entity;

import entity.enums.RolesUsuario;

public class Docente extends Usuario{
    private String siape;
    private String departamento;

    public Docente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, Enum<RolesUsuario> role, String siape, String departamento) {
        super(id, nome, email, senha, papel, ativo, role);
        this.siape = siape;
        this.departamento = departamento;
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

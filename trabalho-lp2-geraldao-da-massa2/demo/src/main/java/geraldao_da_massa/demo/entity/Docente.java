package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Docente extends Usuario{
    @Id
    private Long id;
    private String siape;
    private String departamento;
    private HashSet<Grupo> grupos;


    public Docente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, Enum<RolesUsuario> role, String siape, String departamento) {
        super(id, nome, email, senha, papel, ativo, role);
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

    @OneToOne(optional = false)
    private Oportunidade oportunidade;

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }
}

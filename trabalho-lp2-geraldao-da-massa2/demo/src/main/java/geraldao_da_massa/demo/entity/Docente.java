package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class Docente extends Usuario{
    @Id
    private Long id;
    private String siape;
    private String departamento;
    private HashSet<Grupo> grupos;


    public Docente(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, RolesUsuario role, String siape, String departamento) {
        super(id, nome, email, senha, papel, ativo, role);
        this.siape = siape;
        this.departamento = departamento;
        grupos = new HashSet<>();
    }
}

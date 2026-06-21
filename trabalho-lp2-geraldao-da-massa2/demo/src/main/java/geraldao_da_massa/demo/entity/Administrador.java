package geraldao_da_massa.demo.entity;

import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Table(name = "administrador")
@AllArgsConstructor
public class Administrador extends Usuario {

//    public Administrador(Integer id, String nome, String email, String senha, Papel papel, Boolean ativo, Enum<RolesUsuario> role) {
//        super(id, nome, email, senha, papel, ativo, role);
//    }

}

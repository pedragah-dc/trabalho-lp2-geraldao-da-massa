package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.Papel;
import geraldao_da_massa.demo.entity.enums.RolesUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

}

package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

}

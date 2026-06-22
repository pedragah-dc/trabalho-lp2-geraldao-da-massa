package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}

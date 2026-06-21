package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}

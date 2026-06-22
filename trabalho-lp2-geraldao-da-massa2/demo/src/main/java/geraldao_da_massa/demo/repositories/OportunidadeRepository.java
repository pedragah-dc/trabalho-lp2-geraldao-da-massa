package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Integer> {
}

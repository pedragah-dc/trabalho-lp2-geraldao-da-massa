package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Discente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscenteRepository extends JpaRepository<Discente, Integer> {

}

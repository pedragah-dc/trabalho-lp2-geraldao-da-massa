package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}

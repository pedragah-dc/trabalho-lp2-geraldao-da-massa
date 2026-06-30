package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Administrador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    boolean existsByEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "E-mail inválido.") String email);
}

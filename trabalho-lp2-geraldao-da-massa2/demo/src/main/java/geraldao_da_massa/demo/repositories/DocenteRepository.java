package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Docente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    boolean existsByEmail(@NotBlank(message = "O e-mail é obrigatório.") @Email(message = "E-mail inválido.") String email);

    // Docente findById(int id);
}

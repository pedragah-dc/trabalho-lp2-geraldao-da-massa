package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Grupo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {


    boolean existsGrupoByEmail(@NotBlank @NotNull String email);

}

package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNome(String nome);
}

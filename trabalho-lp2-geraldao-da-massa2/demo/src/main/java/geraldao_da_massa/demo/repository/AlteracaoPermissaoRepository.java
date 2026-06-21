package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.AlteracaoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AlteracaoPermissaoRepository extends JpaRepository<AlteracaoPermissao, Integer> {

}

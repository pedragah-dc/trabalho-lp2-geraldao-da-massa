package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.Oportunidade;
import geraldao_da_massa.demo.entity.enums.TiposModalidade;
import geraldao_da_massa.demo.entity.enums.TiposOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Integer> {

}

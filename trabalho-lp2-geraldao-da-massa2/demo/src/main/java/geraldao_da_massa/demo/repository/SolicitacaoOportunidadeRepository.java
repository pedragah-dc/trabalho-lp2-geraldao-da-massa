package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Discente;
import geraldao_da_massa.demo.entity.SolicitacaoOportunidade;
import geraldao_da_massa.demo.entity.enums.StatusSolicitacaoOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface SolicitacaoOportunidadeRepository extends JpaRepository<SolicitacaoOportunidade, Integer> {

}

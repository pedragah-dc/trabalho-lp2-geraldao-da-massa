package geraldao_da_massa.demo.repository;

import geraldao_da_massa.demo.entity.Aproveitamento;
import geraldao_da_massa.demo.entity.enums.StatusAproveitamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AproveitamentoRepository extends JpaRepository<Aproveitamento, Integer> {

}

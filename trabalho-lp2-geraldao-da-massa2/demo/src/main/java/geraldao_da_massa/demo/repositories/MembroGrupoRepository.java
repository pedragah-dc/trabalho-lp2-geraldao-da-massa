package geraldao_da_massa.demo.repositories;

import geraldao_da_massa.demo.entities.Grupo;
import geraldao_da_massa.demo.entities.MembroGrupo;
import geraldao_da_massa.demo.entities.Usuario;
import org.hibernate.query.criteria.JpaParameterExpression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroGrupoRepository extends JpaRepository<MembroGrupo, Integer> {
    MembroGrupo findByGrupoAndMembro(Grupo grupo, Usuario membro);

//    MembroGrupo findByIdMembro(Integer idMembro);
}

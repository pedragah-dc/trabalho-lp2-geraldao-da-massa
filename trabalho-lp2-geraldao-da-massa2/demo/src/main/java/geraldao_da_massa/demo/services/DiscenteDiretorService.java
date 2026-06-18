package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.entity.DiscenteDiretor;
import geraldao_da_massa.demo.entity.Docente;
import geraldao_da_massa.demo.entity.Oportunidade;
import geraldao_da_massa.demo.entity.enums.TiposModalidade;
import geraldao_da_massa.demo.entity.enums.TiposOportunidade;

import java.time.LocalDateTime;

public class DiscenteDiretorService {

    private final OportunidadesService oportunidadeService;

    public DiscenteDiretorService(OportunidadesService oportunidadeService) {
        this.oportunidadeService = oportunidadeService;
    }

    // DiscenteDiretor cria e já submete para aprovação em um passo
//    public Oportunidade criarESubmeter(String titulo, String descricao,
//                                       TiposOportunidade tipo, TiposModalidade modalidade,
//                                       Integer cargaHoraria, Integer vagas,
//                                       LocalDateTime inicio, LocalDateTime fim,
//                                       LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
//                                       DiscenteDiretor autor, Docente docenteResponsavel) {
//
//        Oportunidade op = oportunidadeService.criarOportunidade(
//                titulo, descricao, tipo, modalidade, cargaHoraria, vagas,
//                inicio, fim, dataInicioInscricoes, dataFimInscricoes,
//                autor, docenteResponsavel);
//
//        oportunidadeService.submeterParaAprovacao(op);
//        return op;
//    }
}

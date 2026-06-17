package services;

import entity.Docente;
import entity.Oportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;
import repository.DocenteRepository;

import java.time.LocalDateTime;

public class DocenteService {

    private final OportunidadesService oportunidadeService;


    public DocenteService(OportunidadesService oportunidadeService) {
        this.oportunidadeService = oportunidadeService;
    }

    // Docente também pode criar oportunidades
    public Oportunidade criarOportunidade(String titulo, String descricao,
                                          TiposOportunidade tipo, TiposModalidade modalidade,
                                          Integer cargaHoraria, Integer vagas,
                                          LocalDateTime inicio, LocalDateTime fim,
                                          LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
                                          Docente autor, Docente docenteResponsavel) {

        return oportunidadeService.criarOportunidade(titulo, descricao, tipo, modalidade,
                cargaHoraria, vagas, inicio, fim,
                dataInicioInscricoes, dataFimInscricoes,
                autor, docenteResponsavel);
    }

    // Docente aprova oportunidade de discente
    public void aprovar(Oportunidade oportunidade, Docente docente) {
        oportunidadeService.aprovarOportunidade(oportunidade, docente);
    }

    // Docente reprova com motivo
    public void reprovar(Oportunidade oportunidade, Docente docente, String motivo) {
        oportunidadeService.reprovarOportunidade(oportunidade, docente, motivo);
    }


    public Boolean verificaSiapeDocente(String siape, DocenteRepository repositorio){
        for (Docente docente : repositorio.listaDocentes) {
            if (docente.getSiape().equals(siape)) {
                return true;
            }
        }
        return false;
    }
}

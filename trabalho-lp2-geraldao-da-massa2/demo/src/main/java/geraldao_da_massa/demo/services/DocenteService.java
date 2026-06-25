package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private OportunidadesService oportunidadeService;


    // Docente também pode criar oportunidades
//    public Oportunidade criarOportunidade(String titulo, String descricao,
//                                          TiposOportunidade tipo, TiposModalidade modalidade,
//                                          Integer cargaHoraria, Integer vagas,
//                                          LocalDateTime inicio, LocalDateTime fim,
//                                          LocalDateTime dataInicioInscricoes, LocalDateTime dataFimInscricoes,
//                                          Docente autor, Docente docenteResponsavel) {
//
//        return oportunidadeService.criarOportunidade(titulo, descricao, tipo, modalidade,
//                cargaHoraria, vagas, inicio, fim,
//                dataInicioInscricoes, dataFimInscricoes,
//                autor, docenteResponsavel);
//    }
    public Oportunidade criarOportunidade(OportunidadeRequestDTO opDTO){
        return null;
        //return oportunidadeService.criarOportunidade(opDTO);
    }


    // Docente aprova oportunidade de discente
    public void aprovar(Oportunidade oportunidade, Docente docente) {
        oportunidadeService.aprovarOportunidade(oportunidade, docente);
    }

    // Docente reprova com motivo
    public void reprovar(Oportunidade oportunidade, Docente docente, String motivo) {
        oportunidadeService.reprovarOportunidade(oportunidade, docente, motivo);
    }


    public Boolean verificaSiapeDocente(String siape){
        try {
            for(Docente doc: docenteRepository.findAll()){

                if(siape.equals(doc.getSiape())) return true;
            }
        } catch (Exception err){
            System.out.println(err.getMessage());
        }
        return false;
    }

}

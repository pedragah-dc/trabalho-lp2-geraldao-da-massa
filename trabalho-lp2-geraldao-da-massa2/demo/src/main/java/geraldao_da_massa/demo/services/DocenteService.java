package geraldao_da_massa.demo.services;

import geraldao_da_massa.demo.DTOs.inputs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.ReprovacaoRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.OportunidadeResponseDTO;
import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import geraldao_da_massa.demo.repositories.OportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private OportunidadesService oportunidadeService;
    @Autowired
    private OportunidadeRepository oportunidadeRepository;



    public OportunidadeResponseDTO criarOportunidade(OportunidadeRequestDTO opDTO){
        return oportunidadeService.criarOportunidade(opDTO);
    }


    // Docente aprova oportunidade de discente
    public OportunidadeResponseDTO aprovar(int idOportunidade, int idDocente) {
        Docente docente = docenteRepository.findById(idDocente).
                orElseThrow(() -> new RuntimeException("NAO EXISTE DOCENTE NESTE ID"));
        Oportunidade oportunidade = oportunidadeRepository.findById(idOportunidade).
                orElseThrow(() -> new RuntimeException("NAO EXISTE OPORTUNIDADE NESTE ID"));

        return oportunidadeService.aprovarOportunidade(oportunidade, docente);
    }

    // Docente reprova com motivo
    //obrigado, azambuja
    public OportunidadeResponseDTO reprovar(int idOportunidade, int idDocente, ReprovacaoRequestDTO motivoDTO) {
        Docente docente = docenteRepository.findById(idDocente).
                orElseThrow(() -> new RuntimeException("NAO EXISTE DOCENTE NESTE ID"));
        Oportunidade oportunidade = oportunidadeRepository.findById(idOportunidade).
                orElseThrow(() -> new RuntimeException("NAO EXISTE OPORTUNIDADE NESTE ID"));
        return oportunidadeService.reprovarOportunidade(oportunidade, docente, motivoDTO.getMotivo());
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

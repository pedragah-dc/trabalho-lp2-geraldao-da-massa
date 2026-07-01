package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.ReprovacaoRequestDTO;
import geraldao_da_massa.demo.entities.Docente;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.repositories.DocenteRepository;
import geraldao_da_massa.demo.services.OportunidadesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    private OportunidadesService oportunidadesService;
    @Autowired
    private DocenteRepository docenteRepository;


    // RF011 - Criação
    // POST /oportunidades/{id}
    @PostMapping("/{id}")
    public boolean addOportunidade(@Valid @RequestBody OportunidadeRequestDTO oportunidade, @PathVariable int id){
        return oportunidadesService.criarOportunidade(oportunidade, id);
    }

    // GET /oportunidades
    @GetMapping
    public List<Oportunidade> listarOportunidades(){
        return oportunidadesService.listarTodas();
    }

    // RF011 - Submeter oportunidade (RASCUNHO -> AGUARDANDO_APROVACAO)
    // PATCH /oportunidades/5/submeter
    @PatchMapping("/{idOportunidade}/submeter")
    public Oportunidade submeter(@PathVariable Integer idOportunidade) {
        Oportunidade oportunidade = oportunidadesService.buscarPorId(idOportunidade);
        oportunidadesService.submeterParaAprovacao(oportunidade);
        return oportunidade;
    }

    // RF012 - Docente aprova a oportunidade
    // PATCH /oportunidades/5/aprovar?idDocente=2
    @PatchMapping("/{idOportunidade}/aprovar")
    public Oportunidade aprovar(@PathVariable Integer idOportunidade, @RequestParam Integer idDocente) {
        Oportunidade oportunidade = oportunidadesService.buscarPorId(idOportunidade);
        Docente docente = buscarDocente(idDocente);

        oportunidadesService.aprovarOportunidade(oportunidade, docente);
        return oportunidade;
    }

    // RF012 - Docente reprova a oportunidade com motivo obrigatório
    // PATCH /oportunidades/5/reprovar?idDocente=2
    @PatchMapping("/{idOportunidade}/reprovar")
    public Oportunidade reprovar(@PathVariable Integer idOportunidade, @RequestParam Integer idDocente,
                                  @Valid @RequestBody ReprovacaoRequestDTO dto) {
        Oportunidade oportunidade = oportunidadesService.buscarPorId(idOportunidade);
        Docente docente = buscarDocente(idDocente);

        oportunidadesService.reprovarOportunidade(oportunidade, docente, dto.getMotivo());
        return oportunidade;
    }

    // Lista oportunidades com inscrições abertas
    // GET /oportunidades/abertas
    @GetMapping("/abertas")
    public List<Oportunidade> listarAbertas() {
        return oportunidadesService.listarOportunidadesAbertas();
    }

    // Lista oportunidades aguardando aprovação de um docente específico
    // GET /oportunidades/aguardando-aprovacao?idDocente=2
    @GetMapping("/aguardando-aprovacao")
    public List<Oportunidade> listarAguardandoAprovacao(@RequestParam Integer idDocente) {
        Docente docente = buscarDocente(idDocente);
        return oportunidadesService.listarAguardandoAprovacao(docente);
    }


    //hmmm
    private Docente buscarDocente(Integer id) {
        Docente docente = docenteRepository.findById(id.intValue()).
                orElseThrow(() -> new RuntimeException("NAO EXISTE DOCENTE NESTE ID"));
        return docente;
    }
}

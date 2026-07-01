package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.DocenteRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.ReprovacaoRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.DocenteResponseDTO;
import geraldao_da_massa.demo.DTOs.outputs.OportunidadeResponseDTO;
import geraldao_da_massa.demo.services.DocenteService;
import geraldao_da_massa.demo.services.OportunidadesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Docente")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;


    @PostMapping("/oportunidade/criar")
    public OportunidadeResponseDTO criarOportunidade(@Valid @RequestBody OportunidadeRequestDTO dto){
        return docenteService.criarOportunidade(dto);
    }
    @PostMapping("{idDocente}/oportunidades/{idOportunidade}/aprovar")
    public OportunidadeResponseDTO aprovar(@PathVariable int idOportunidade, @PathVariable int idDocente){
        return docenteService.aprovar(idOportunidade, idDocente);
    }
    @PostMapping("{idDocente}/oportunidades/{idOportunidade}/reprovar")
    public OportunidadeResponseDTO reprovar(@PathVariable int idOporunidade, @PathVariable int idDocente, @Valid @RequestBody ReprovacaoRequestDTO dto){
        return docenteService.reprovar(idOporunidade, idOporunidade, dto);
    }
}

package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.OportunidadeRequestDTO;
import geraldao_da_massa.demo.entities.Oportunidade;
import geraldao_da_massa.demo.services.OportunidadesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oportunidade")
public class OportunidadeController {

    @Autowired
    private OportunidadesService oportunidadesService;


    @PostMapping("/{id}")
    public boolean addOportunidade(@Valid @RequestBody OportunidadeRequestDTO oportunidade, @PathVariable int id){
        return oportunidadesService.criarOportunidade(oportunidade, id);
    }
    @GetMapping("/listar")
    public List<Oportunidade> listarOportunidades(){
        return oportunidadesService.listarTodas();
    }
}

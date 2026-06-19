package geraldao_da_massa.demo.controller;

import geraldao_da_massa.demo.DTOS.OportunidadeRequestDTO;
import geraldao_da_massa.demo.entity.Oportunidade;
import geraldao_da_massa.demo.services.OportunidadesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OportunidadeController {

    @Autowired
    private OportunidadesService oportunidadesService;

    @PostMapping("/oportunidades")
    public boolean addOportunidade(@Valid @RequestBody OportunidadeRequestDTO oportunidade){
        return oportunidadesService.criarOportunidade(oportunidade);
    }
    @GetMapping("/oportunidades")
    public List<Oportunidade> listarOportunidades(){
        return oportunidadesService.listarTodas();
    }
}

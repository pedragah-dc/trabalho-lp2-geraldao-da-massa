package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.DocenteRequestDTO;
import geraldao_da_massa.demo.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("{id}/docentes")
    public void cadastrarDocente(@PathVariable Integer idAdm, @RequestBody DocenteRequestDTO docDTO, @PathVariable String id){
        administradorService.cadastroDocente(idAdm, docDTO);
    }
}

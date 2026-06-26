package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.DocenteRequestDTO;
import geraldao_da_massa.demo.DTOs.RequestPPCDTO;
import geraldao_da_massa.demo.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/{idAdm}/docentes")
    public void cadastrarDocente(@PathVariable Integer idAdm, @RequestBody DocenteRequestDTO docDTO){
        administradorService.cadastroDocente(idAdm, docDTO);
    }
    @PostMapping("/{idAdm}/cursoppc")
    public void cadastrarPPC(@PathVariable int idAdm, @RequestBody RequestPPCDTO ppcDTO){
        administradorService.cadastrarPPC(idAdm, ppcDTO);
    }
}

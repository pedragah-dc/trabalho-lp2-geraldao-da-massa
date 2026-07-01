package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.DocenteRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.PPCrequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.UsuarioRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.DocenteResponseDTO;
import geraldao_da_massa.demo.DTOs.outputs.UsuarioResponseDTO;
import geraldao_da_massa.demo.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/{idAdm}/docentes")
    public DocenteResponseDTO cadastrarDocente(@PathVariable Integer idAdm, @RequestBody DocenteRequestDTO docDTO){
        return administradorService.cadastroDocente(idAdm, docDTO);
    }
    @PostMapping("/{idAdm}/cursoppc")
    public void cadastrarPPC(@PathVariable int idAdm, @RequestBody PPCrequestDTO ppcDTO){
        administradorService.cadastrarPPC(idAdm, ppcDTO);
    }
    @PostMapping("/cadastrar")
    public UsuarioResponseDTO cadastrarAdministrador(@RequestBody UsuarioRequestDTO dto){
        return administradorService.cadastrarAdmin(dto);
    }
}

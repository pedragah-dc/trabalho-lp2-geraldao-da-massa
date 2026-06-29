package geraldao_da_massa.demo.controllers;


import geraldao_da_massa.demo.DTOs.inputs.GrupoRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.MembroGrupoRequestDTO;
import geraldao_da_massa.demo.DTOs.inputs.UsuarioRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.GrupoResponseDTO;
import geraldao_da_massa.demo.DTOs.outputs.MembroGrupoResponseDTO;
import geraldao_da_massa.demo.services.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;

    @PostMapping("/{idgrupo}")
    public MembroGrupoResponseDTO addMembro(@PathVariable int idgrupo, int idUser){
        return grupoService.adicionarMembro(idgrupo, idUser);
    }
    @PostMapping("/criar")
    public GrupoResponseDTO criargrupo(@Valid @RequestBody GrupoRequestDTO dto){
        return grupoService.criarGrupo(dto);
    }
    @PostMapping("/cargomembro/{idmembro}")
    public MembroGrupoResponseDTO setCargo(@PathVariable int idmembro, @Valid @RequestBody MembroGrupoRequestDTO membroDTO){
        return grupoService.setCargo(idmembro, membroDTO.getCargo());
    }
}

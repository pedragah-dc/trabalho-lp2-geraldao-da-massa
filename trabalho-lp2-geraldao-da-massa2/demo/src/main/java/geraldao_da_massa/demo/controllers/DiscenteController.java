package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.DiscenteRequestDTO;
import geraldao_da_massa.demo.DTOs.outputs.DiscenteResponseDTO;
import geraldao_da_massa.demo.services.DiscenteService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discente")
public class DiscenteController {
    @Autowired
    private DiscenteService discenteService;

    @PostMapping("/cadastrar")
    public DiscenteResponseDTO cadastrarDiscente(@Valid @RequestBody DiscenteRequestDTO dto){
        return discenteService.autocadastroDiscente(dto);
    }

}

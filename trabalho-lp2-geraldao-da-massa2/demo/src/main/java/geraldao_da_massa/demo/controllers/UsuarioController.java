package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.DTOs.inputs.UsuarioRequestDTO;
import geraldao_da_massa.demo.entities.Usuario;
import geraldao_da_massa.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody UsuarioRequestDTO userDTO){
        return usuarioService.autocadastroUsuario(userDTO);
    }
}

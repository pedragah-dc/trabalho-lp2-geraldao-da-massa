package geraldao_da_massa.demo.controller;

import geraldao_da_massa.demo.DTOS.UsuarioRequestDTO;
import geraldao_da_massa.demo.entity.Usuario;
import geraldao_da_massa.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("api/Usuario")
    public Usuario salvarUsuario(@RequestBody UsuarioRequestDTO userDTO){
        return usuarioService.autocadastroUsuario(userDTO);
    }
}

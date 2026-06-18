package geraldao_da_massa.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/oi")
    public String ola(){
        return "Olaaaaaaaaa";
    }
    //
}

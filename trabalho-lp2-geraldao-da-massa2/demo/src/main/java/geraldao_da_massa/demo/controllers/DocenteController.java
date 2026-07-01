package geraldao_da_massa.demo.controllers;

import geraldao_da_massa.demo.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Docente")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;

}

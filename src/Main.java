import entity.*;
import repository.CursoRepository;
import repository.DiscenteRepository;
import repository.DocenteRepository;
import repository.OportunidadeRepository;
import services.InscricaoService;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //simular que  um discente quer se inscrever numa oportunidade
    public static void main(String[] args) {
        //carregar repositorios
        List<Discente> listDis = new DiscenteRepository().listaDiscente;
        List<Docente> listDoc = new DocenteRepository().listaDocentes;
        List<Curso> repoCurso = new CursoRepository().listaCursos;
        List<Oportunidade> opRepo = new OportunidadeRepository().listaOportunidades;

        //discente se inscreve numa oportunidade
        InscricaoService serviceIns = new InscricaoService();
        serviceIns.criarInscricao(opRepo.get(0), listDis.get(0), "sla sla");

        //
        System.out.println("vc foi inscritor em: "+serviceIns.getInscricao());



    }
}
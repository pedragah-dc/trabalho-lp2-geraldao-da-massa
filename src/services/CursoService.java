package services;

import entity.Curso;

import java.util.List;
import java.util.Scanner;

import static utils.ConsoleUtils.lerStringValida;


public class CursoService {

    public Curso escolheCursos(List<Curso> lista, Scanner sc){

        for(Curso curso: lista){
            System.out.println(curso.getCodigo() + " - " + curso.getNome());
        }

        String curso = lerStringValida(sc, "insira o codigo do curso: ");

        for(Curso curso1: lista){
            if (curso1.getCodigo().toString().equals(curso)){
                System.out.println("inserção válida! ");
                return curso1;
            }
        }
        System.out.println("curso inválido! ");
        return null;
    }
}

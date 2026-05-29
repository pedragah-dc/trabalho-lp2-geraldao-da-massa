package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.time.LocalDateTime;
import java.util.List;

public class DocenteServiceTest {

    public static void main(String[] args) {

        Papel papelDocente = new Papel("DOCENTE");
        Docente docente1 = new Docente(1, "Prof. Silva", "silva@ufma.br", "123", papelDocente, true, "1111", "CC");
        Docente docente2 = new Docente(2, "Prof. Lima",  "lima@ufma.br",  "123", papelDocente, true, "2222", "SI");

        OportunidadeRepository repo    = new OportunidadeRepository();
        OportunidadesService   service = new OportunidadesService(repo);
        DocenteService         docSrv  = new DocenteService(service);

        System.out.println("=== Docente cria oportunidade ===");
        Oportunidade op = docSrv.criarOportunidade(
                "Seminário de IA", "Palestras sobre IA",
                TiposOportunidade.EVENTO, TiposModalidade.PRESENCIAL,
                8, 50,
                LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6),
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(4),
                docente1, docente1);

        System.out.println("Status: " + op.getStatus());

        System.out.println("\n=== Submete e aprova ===");
        service.submeterParaAprovacao(op);
        docSrv.aprovar(op, docente1);
        System.out.println("Status: " + op.getStatus());

        System.out.println("\n=== Listagem de oportunidades ===");
        List<Oportunidade> todas = service.listarTodas();
        for (Oportunidade o : todas) {
            System.out.println("  - " + o.getTitulo() + " | Status: " + o.getStatus());
        }

        System.out.println("\n✓ DocenteServiceTest concluído.");
    }
}

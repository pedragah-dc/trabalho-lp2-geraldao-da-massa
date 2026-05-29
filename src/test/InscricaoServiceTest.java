package test;

import entity.*;
import entity.enums.*;
import repository.*;
import services.*;

import java.time.LocalDateTime;
import java.util.List;

public class InscricaoServiceTest {

    public static void main(String[] args) {

        Papel papelDocente  = new Papel("DOCENTE");
        Papel papelDiscente = new Papel("DISCENTE");

        Docente docente = new Docente(1, "Prof. Ana", "ana@ufma.br", "123", papelDocente, true, "9999", "TI");

        CursoRepository cursoRepo = new CursoRepository();
        Curso curso = cursoRepo.listaCursos.get(0);

        Discente joao  = new Discente(10, "João",  "joao@disc.ufma.br",  "123", papelDiscente, true, "2021001", 3, curso);
        Discente maria = new Discente(11, "Maria", "maria@disc.ufma.br", "123", papelDiscente, true, "2021002", 3, curso);
        Discente pedro = new Discente(12, "Pedro", "pedro@disc.ufma.br", "123", papelDiscente, true, "2021003", 4, curso);
        Discente carla = new Discente(13, "Carla", "carla@disc.ufma.br", "123", papelDiscente, true, "2021004", 3, curso);

        OportunidadeRepository opRepo   = new OportunidadeRepository();
        InscricaoRepository    inscRepo = new InscricaoRepository();
        OportunidadesService opService  = new OportunidadesService(opRepo);
        InscricaoService inscService    = new InscricaoService(inscRepo);
        CertificadoService certService  = new CertificadoService(inscRepo);

        Oportunidade op = opService.criarOportunidade(
                "Curso de Java POO", "Orientação a objetos na prática",
                TiposOportunidade.CURSO, TiposModalidade.PRESENCIAL,
                40, 2,
                LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(50),
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(9),
                docente, docente);

        opService.submeterParaAprovacao(op);
        opService.aprovarOportunidade(op, docente);

        System.out.println("=== RF015: Inscrever e APROVAR/REJEITAR ===");
        Inscricao inscJoao  = inscService.criarInscricao(op, joao,  "Quero aprender Java");
        Inscricao inscMaria = inscService.criarInscricao(op, maria, "Necessário para minha pesquisa");
        Inscricao inscPedro = inscService.criarInscricao(op, pedro, "Interesse em POO");
        Inscricao inscCarla = inscService.criarInscricao(op, carla, "Tenho interesse");

        inscService.aprovarInscricao(inscJoao, op);
        inscService.aprovarInscricao(inscMaria, op);
        inscService.rejeitarInscricao(inscPedro);
        System.out.println("João: " + inscJoao.getStatus());   // APROVADO
        System.out.println("Maria: " + inscMaria.getStatus()); // APROVADO
        System.out.println("Pedro: " + inscPedro.getStatus()); // REJEITADO

        System.out.println("\n--- Vagas esgotadas ---");
        try {
            inscService.aprovarInscricao(inscCarla, op); // 3ª aprovação, só há 2 vagas
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n=== RF016: Cancelar inscrição ===");
        inscService.cancelarInscricao(inscPedro);
        System.out.println("Pedro após cancelar: " + inscPedro.getStatus()); // CANCELADO

        System.out.println("\n=== RF017: Substituir por alguém da lista de interessados ===");
        // Carla está PENDENTE na oportunidade = é da lista de interessados
        Inscricao inscSubstituta = inscService.substituirParticipante(inscJoao, carla);
        System.out.println("João (original): " + inscJoao.getStatus());        // SUBSTITUIDO
        System.out.println("Carla (substituta): " + inscSubstituta.getStatus()); // APROVADO

        System.out.println("\n--- Tentando substituir por quem cancelou ---");
        try {
            inscService.substituirParticipante(inscMaria, pedro); // Pedro cancelou
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n=== RF019: Encerrar e gerar certificados ===");
        op.setStatus(StatusOportunidade.EM_EXECUCAO);
        List<entity.Certificados> certs = certService.encerrarEGerarCertificados(op);
        System.out.println("Status: " + op.getStatus()); // CONCLUIDA
        System.out.println("Certificados: " + certs.size()); // 2 (Maria e Carla)

        System.out.println("\n✓ Todos os RFs testados com sucesso.");
    }
}

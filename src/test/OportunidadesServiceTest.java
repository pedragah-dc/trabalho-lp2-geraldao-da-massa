package test;

import entity.Docente;
import entity.Oportunidade;
import entity.Papel;
import entity.enums.StatusOportunidade;
import entity.enums.TiposModalidade;
import entity.enums.TiposOportunidade;
import repository.OportunidadeRepository;
import services.OportunidadesService;

import java.time.LocalDateTime;

public class OportunidadesServiceTest {

    public static void main(String[] args) {

        // --- Montando o cenário de teste ---
        Papel papelDocente = new Papel("DOCENTE");
        Docente docente = new Docente(1, "Prof. Carlos", "carlos@ufma.br", "123", papelDocente, true, "1234", "Computação");

        OportunidadeRepository repo = new OportunidadeRepository();
        OportunidadesService service = new OportunidadesService(repo);

        System.out.println("=== RF011: Criando oportunidade ===");
        Oportunidade op = service.criarOportunidade(
                "Workshop de Java",
                "Aprenda POO na prática",
                TiposOportunidade.OFICINA,
                TiposModalidade.PRESENCIAL,
                20,
                30,
                LocalDateTime.now().plusDays(15),
                LocalDateTime.now().plusDays(45),
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(10),
                docente,
                docente
        );
        System.out.println("Status após criar: " + op.getStatus()); // deve ser RASCUNHO

        System.out.println("\n=== RF011: Submetendo para aprovação ===");
        service.submeterParaAprovacao(op);
        System.out.println("Status após submeter: " + op.getStatus()); // deve ser AGUARDANDO_APROVACAO

        System.out.println("\n=== RF012: Docente APROVA a oportunidade ===");
        service.aprovarOportunidade(op, docente);
        System.out.println("Status após aprovação: " + op.getStatus()); // deve ser APROVADA ou EM_INSCRICOES

        System.out.println("\n=== RF012: Testando reprovação ===");
        // Cria outra oportunidade para testar reprovação
        Oportunidade op2 = service.criarOportunidade(
                "Evento de extensão",
                "Descrição",
                TiposOportunidade.EVENTO,
                TiposModalidade.REMOTO,
                8,
                50,
                LocalDateTime.now().plusDays(20),
                LocalDateTime.now().plusDays(21),
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(15),
                docente,
                docente
        );
        service.submeterParaAprovacao(op2);
        service.reprovarOportunidade(op2, docente, "Carga horária insuficiente para o tipo de atividade.");
        System.out.println("Status após reprovação: " + op2.getStatus()); // deve ser REPROVADA
        System.out.println("Feedback: " + op2.getFeedbackReprovacao());

        System.out.println("\n=== Tentando submeter novamente uma reprovada ===");
        try {
            service.submeterParaAprovacao(op2); // deve lançar exceção
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado capturado: " + e.getMessage());
        }

        System.out.println("\n=== Validações de campos obrigatórios ===");
        try {
            service.criarOportunidade("", null, null, null, 0, 0,
                    null, null, null, null, docente, docente);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado capturado: " + e.getMessage());
        }

        System.out.println("\n✓ Testes de RF011 e RF012 concluídos.");
    }
}

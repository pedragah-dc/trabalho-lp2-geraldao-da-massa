package geraldao_da_massa.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//eita kkkk
// Captura exceções lançadas pelos services e devolve respostas HTTP legíveis
// em vez do erro 500 genérico do Spring
@RestControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException -> 400 Bad Request
    // Ex: "Título da oportunidade é obrigatório", "Docente não encontrado com id: X"
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro(ex.getMessage()));
    }

    // IllegalStateException -> 409 Conflict
    // Ex: "Discente já possui inscrição", "Não há vagas disponíveis", "Status incorreto"
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro(ex.getMessage()));
    }

    // Erros de validação do @Valid nos DTOs -> 400 Bad Request com detalhe por campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erros.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    // Qualquer outro erro inesperado -> 500 com mensagem legível
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro(ex.getMessage()));
    }

    private Map<String, String> erro(String mensagem) {
        Map<String, String> body = new HashMap<>();
        body.put("erro", mensagem);
        return body;
    }
}

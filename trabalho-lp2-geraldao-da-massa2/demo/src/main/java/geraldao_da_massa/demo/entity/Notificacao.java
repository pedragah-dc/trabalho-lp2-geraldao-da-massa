package geraldao_da_massa.demo.entity;


public class Notificacao {
    private Integer id;
    private String mensagem;
    private Usuario destinatario;
    private Boolean lida;

    public Notificacao(Integer id, String mensagem, Usuario destinatario, Boolean lida) {
        this.id = id;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
        this.lida = lida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }
}

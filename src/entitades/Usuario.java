package entitades;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Papel papel;
    private Boolean ativo;

    public Usuario(String nome, String email, String senha, Papel papel, Boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = ativo;
    }
}

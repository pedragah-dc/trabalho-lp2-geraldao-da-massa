package entity;

import entity.enums.TipoOperacao;

import java.time.LocalDateTime;

public class AlteracaoPermissao {
    private Usuario usuario;
    private LocalDateTime dataAlteracao;
    private TipoOperacao tipoOperacao;

    private String nomePPC;

    public AlteracaoPermissao(Usuario usuario, LocalDateTime dataAlteracao, TipoOperacao tipoOperacao,  String nomePPC) {
        this.usuario = usuario;
        this.dataAlteracao = dataAlteracao;
        this.tipoOperacao = tipoOperacao;
        this.nomePPC = nomePPC;
    }

    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public LocalDateTime getData(){
        return dataAlteracao;
    }
    public void setData(LocalDateTime dataAlteracao){
        this.dataAlteracao = dataAlteracao;
    }

    public TipoOperacao getTipoOperacao(){
        return tipoOperacao;
    }
    public void setTipoOperacao(TipoOperacao tipoOperacao){
        this.tipoOperacao = tipoOperacao;
    }

    public String getNomePPC(){
        return nomePPC;
    }
    public void setNomePPC(String nomePPC){
        this.nomePPC = nomePPC;
    }
}

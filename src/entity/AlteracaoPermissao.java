package entity;

import entity.enums.TipoOperacao;

import java.time.LocalDateTime;

public class AlteracaoPermissao {
    Usuario usuario;
    LocalDateTime dataAlteracao;
    TipoOperacao tipoOperacao;


    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public LocalDateTime getDataAlteracao(){
        return dataAlteracao;
    }
    public void setDataAlteracao(LocalDateTime dataAlteracao){
        this.dataAlteracao = dataAlteracao;
    }

    public TipoOperacao getTipoOperacao(){
        return tipoOperacao;
    }
    public void setTipoOperacao(TipoOperacao tipoOperacao){
        this.tipoOperacao = tipoOperacao;
    }

}

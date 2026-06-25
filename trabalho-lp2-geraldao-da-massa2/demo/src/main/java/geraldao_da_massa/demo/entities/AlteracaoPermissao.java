package geraldao_da_massa.demo.entities;

import geraldao_da_massa.demo.entities.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AlterarPermissao")
public class AlteracaoPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPermissao;
    @OneToOne
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

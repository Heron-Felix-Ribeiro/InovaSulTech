package br.com.senac.inovasultech.useCases.agentes.domains;

import br.com.senac.inovasultech.entitys.Endereco;
import br.com.senac.inovasultech.entitys.TipoAgente;

import java.util.List;

public class AgenteRequestDom {

    private String nomeAgente;
    private String cnpj;
    private String descricao;
    private TipoAgente tipoAgente;
    private List<Endereco> enderecos;

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoAgente getTipoAgente() {
        return tipoAgente;
    }

    public void setTipoAgente(TipoAgente tipoAgente) {
        this.tipoAgente = tipoAgente;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}

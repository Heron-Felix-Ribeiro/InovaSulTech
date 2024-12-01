package br.com.senac.inovasultech.dto;

import java.util.List;

public class AgenteListDTO {

    private Long id;
    private String nomeAgente;
    private String cnpj;
    private String descricao;
    private String tipoAgente;
    private List<EnderecoDTO> enderecos;


    public AgenteListDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTipoAgente() {
        return tipoAgente;
    }

    public void setTipoAgente(String tipoAgente) {
        this.tipoAgente = tipoAgente;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }


}

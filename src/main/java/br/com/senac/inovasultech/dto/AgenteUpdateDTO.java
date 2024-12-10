package br.com.senac.inovasultech.dto;

import br.com.senac.inovasultech.entitys.Agente;

public class AgenteUpdateDTO {

    private Long id;
    private String nomeAgente;
    private String cnpj;
    private String descricao;
    private String tipoAgente;

    public AgenteUpdateDTO(Agente agente) {
        this.id = id;
        this.nomeAgente = nomeAgente;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.tipoAgente = tipoAgente;
    }

    public AgenteUpdateDTO(){

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
}

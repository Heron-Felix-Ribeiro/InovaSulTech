package br.com.senac.inovasultech.dto;


import br.com.senac.inovasultech.entitys.Agente;

public class AgenteCreateDTO {

    private Long id;
    private String nomeAgente;
    private String cnpj;
    private String descricao;
    private String tipoAgente;
    private String endereco;

    public AgenteCreateDTO(Agente agente) {

        if (agente != null) {

            this.id = agente.getId();
            this.nomeAgente = agente.getNomeAgente();
            this.cnpj = agente.getCnpj();
            this.descricao = agente.getDescricao();
            this.tipoAgente = agente.getTipoAgente().getNmTipoAgente();
            this.endereco = agente.getEnderecos().get(0).getRua();
        }
    }

    public AgenteCreateDTO(){

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

package br.com.senac.inovasultech.useCases.cidade.domains;

public class CidadeResponseDom {

    private Long id;
    private String nomeCidade;
    private String estado;

    public CidadeResponseDom(Long id, String nomeCidade, String estado) {
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public CidadeResponseDom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

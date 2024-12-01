package br.com.senac.inovasultech.useCases.cidade.domains;

public class CidadeRequestDom {

    private String nomeCidade;
    private String estado;

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

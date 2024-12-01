package br.com.senac.inovasultech.dto;

import br.com.senac.inovasultech.entitys.Endereco;

public class EnderecoDTO {

    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;

    public EnderecoDTO(Endereco endereco){

        if (endereco != null) {
            this.id = endereco.getId();
            this.rua = endereco.getRua();
            this.bairro = endereco.getBairro();
            this.cep = endereco.getCep();
            this.cidade = endereco.getCidade().getNomeCidade();
        }
    }

    public EnderecoDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

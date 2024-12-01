package br.com.senac.inovasultech.useCases.tipoAgente.domains;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TipoAgenteResponseDom {

    private Long id;
    private String nmTipoAgente;

    public TipoAgenteResponseDom(Long id, String nmTipoAgente) {
        this.id = id;
        this.nmTipoAgente = nmTipoAgente;
    }

    public TipoAgenteResponseDom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmTipoAgente() {
        return nmTipoAgente;
    }

    public void setNmTipoAgente(String nmTipoAgente) {
        this.nmTipoAgente = nmTipoAgente;
    }
}

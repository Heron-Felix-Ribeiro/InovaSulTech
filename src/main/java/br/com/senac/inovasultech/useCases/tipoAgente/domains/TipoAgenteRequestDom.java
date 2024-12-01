package br.com.senac.inovasultech.useCases.tipoAgente.domains;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TipoAgenteRequestDom {

    private String nmTipoAgente;

    public String getNmTipoAgente() {
        return nmTipoAgente;
    }

    public void setNmTipoAgente(String nmTipoAgente) {
        this.nmTipoAgente = nmTipoAgente;
    }
}

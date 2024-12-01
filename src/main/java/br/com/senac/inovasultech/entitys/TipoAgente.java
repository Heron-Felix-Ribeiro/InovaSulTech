package br.com.senac.inovasultech.entitys;

import jakarta.persistence.*;

@Entity
public class TipoAgente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nmTipoAgente;

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

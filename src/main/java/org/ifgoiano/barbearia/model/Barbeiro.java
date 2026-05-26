package org.ifgoiano.barbearia.model;

public class Barbeiro {
    private Integer idBarbeiro;
    private String nome;

    public Barbeiro(){}
    public Barbeiro(String nome) {
        this.nome = nome;
    }

    public Integer getIdBarbeiro() {
        return idBarbeiro;
    }

    public void setIdBarbeiro(Integer idBarbeiro) {
        this.idBarbeiro = idBarbeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

package org.ifgoiano.barbearia.model;

import java.sql.Date;

public class Atendimento {
    private Integer idAtendimento;

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    private Cliente cliente;
    private Barbeiro barbeiro;
    private Date data;
    private Double valorTotal;
    public Atendimento(){}
    public Atendimento(Cliente cliente, Barbeiro barbeiro, Date data, Double valorTotal) {
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

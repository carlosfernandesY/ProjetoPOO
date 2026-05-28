package org.ifgoiano.barbearia.service;

import java.util.List;

import org.ifgoiano.barbearia.dao.AtendimentoDAO;
import org.ifgoiano.barbearia.model.Atendimento;

import java.util.List;

public class AtendimentoService {
    private final AtendimentoDAO atendimentoDAO;

    public AtendimentoService(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public boolean createAtendimento(Atendimento atendimento) {
        if (atendimento == null) {
            throw new IllegalArgumentException("O atendimento não pode ser nulo.");
        }

        if (atendimento.getCliente() == null || atendimento.getCliente().getIdCliente() <= 0) {
            throw new IllegalArgumentException("É obrigatório selecionar um cliente para o atendimento.");
        }

        if (atendimento.getBarbeiro() == null || atendimento.getBarbeiro().getIdBarbeiro() <= 0) {
            throw new IllegalArgumentException("É obrigatório selecionar um barbeiro para o atendimento.");
        }
        

        return this.atendimentoDAO.create(atendimento);
    }

    public Atendimento readAtendimento(int idAtendimento) {
        if (idAtendimento <= 0) {
            throw new IllegalArgumentException("ID do atendimento inválido.");
        }
        return this.atendimentoDAO.readById(idAtendimento);
    }

    public void updateAtendimento(Atendimento atendimento) {
        if (atendimento == null || atendimento.getIdAtendimento() <= 0) {
            throw new IllegalArgumentException("Atendimento inválido para atualização.");
        }

        if (atendimento.getCliente() == null || atendimento.getBarbeiro() == null || atendimento.getData() == null) {
            throw new IllegalArgumentException("Cliente, barbeiro e data são obrigatórios na edição.");
        }

        this.atendimentoDAO.updateById(atendimento);
    }

    public void deleteAtendimento(Atendimento atendimento) {
        if (atendimento == null || atendimento.getIdAtendimento() <= 0) {
            throw new IllegalArgumentException("Atendimento inválido para exclusão.");
        }
        this.atendimentoDAO.deleteById(atendimento);
    }

    public List<Atendimento> readAllAtendimento() {
        return this.atendimentoDAO.readAll();
    }
}
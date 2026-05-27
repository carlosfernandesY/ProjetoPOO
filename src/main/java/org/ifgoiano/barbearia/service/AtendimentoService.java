package org.ifgoiano.barbearia.service;

import org.ifgoiano.barbearia.dao.AtendimentoDAO;
import org.ifgoiano.barbearia.model.Atendimento;

public class AtendimentoService {
    private final AtendimentoDAO atendimentoDAO;

    public AtendimentoService(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public void createAtendimento(Atendimento atendimento) {
        this.atendimentoDAO.create(atendimento);
    }

    public void readAtendimento(int idAtendimento) {
        this.atendimentoDAO.readById(idAtendimento);
    }

    public void updateAtendimento(Atendimento atendimento) {
        this.atendimentoDAO.updateById(atendimento);

    }

    public void deleteAtendimento(Atendimento atendimento) {
        this.atendimentoDAO.deleteById(atendimento);
    }
}

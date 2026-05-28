package org.ifgoiano.barbearia.service;

import java.util.List;

import org.ifgoiano.barbearia.dao.ServicoDAO;
import org.ifgoiano.barbearia.model.Servico;

public class ServicoService {
    private final ServicoDAO servicoDAO;

    public ServicoService(ServicoDAO servicoDAO) {
        this.servicoDAO = servicoDAO;
    }

    public void createServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O serviço não pode ser nulo.");
        }
        if (servico.getNome() == null || servico.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do serviço é obrigatório.");
        }

        if (servico.getPreco() == null || servico.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do serviço deve ser um valor válido (maior ou igual a zero).");
        }

        this.servicoDAO.create(servico);
    }

    public Servico readServico(int idServico) {
        if (idServico <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido. Deve ser maior que zero.");
        }
        
        return this.servicoDAO.readById(idServico);
    }

    public void updateServico(Servico servico) {
        if (servico == null || servico.getIdServico() <= 0) {
            throw new IllegalArgumentException("Serviço inválido para atualização.");
        }

        if (servico.getNome() == null || servico.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do serviço não pode ser apagado na atualização.");
        }

        if (servico.getPreco() == null || servico.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do serviço não pode ser alterado para um valor negativo.");
        }

        this.servicoDAO.updateById(servico);
    }

    public void deleteServico(Servico servico) {
        if (servico == null || servico.getIdServico() <= 0) {
            throw new IllegalArgumentException("Serviço inválido para exclusão.");
        }


        this.servicoDAO.deleteById(servico);
    }

    public List<Servico> readAllServico() {
        return this.servicoDAO.readAll();
    }
}
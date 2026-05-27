package org.ifgoiano.barbearia.view.atendimento;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.dao.AtendimentoDAO;

import org.ifgoiano.barbearia.model.Atendimento;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.model.Cliente;

import org.ifgoiano.barbearia.view.components.AlertComponent;

import java.sql.Date;
import java.time.LocalDate;

public class AtendimentoController {

    private final AtendimentoDAO atendimentoDAO =
            new AtendimentoDAO();

    public void salvar(
            Cliente cliente,
            Barbeiro barbeiro,
            String valor,
            LocalDate data,
            TableView<Atendimento> tabela
    ) {

        try {

            Atendimento atendimento =
                    new Atendimento();

            atendimento.setCliente(cliente);

            atendimento.setBarbeiro(barbeiro);

            atendimento.setValorTotal(
                    Double.parseDouble(valor)
            );

            atendimento.setData(
                    Date.valueOf(data)
            );

            boolean sucesso =
                    atendimentoDAO.create(atendimento);

            if (sucesso) {

                atualizarTabela(tabela);

                AlertComponent.sucesso(
                        "Atendimento salvo com sucesso."
                );

            } else {

                AlertComponent.erro(
                        "Falha ao salvar atendimento."
                );
            }

        } catch (Exception e) {

            AlertComponent.erro(
                    "Erro ao salvar atendimento."
            );
        }
    }

    public void atualizarTabela(
            TableView<Atendimento> tabela
    ) {

        tabela.setItems(
                FXCollections.observableArrayList(
                        atendimentoDAO.readAll()
                )
        );
    }
}
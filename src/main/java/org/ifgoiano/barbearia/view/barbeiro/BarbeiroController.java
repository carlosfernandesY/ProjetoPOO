package org.ifgoiano.barbearia.view.barbeiro;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.view.components.AlertComponent;

public class BarbeiroController {

    private final BarbeiroDAO barbeiroDAO =
            new BarbeiroDAO();

    public void salvar(
            String nome,
            TableView<Barbeiro> tabela
    ) {

        try {

            Barbeiro barbeiro = new Barbeiro();

            barbeiro.setNome(nome);

            boolean sucesso =
                    barbeiroDAO.create(barbeiro);

            if (sucesso) {

                atualizarTabela(tabela);

                AlertComponent.sucesso(
                        "Barbeiro salvo com sucesso."
                );

            } else {

                AlertComponent.erro(
                        "Falha ao salvar barbeiro."
                );
            }

        } catch (Exception e) {

            AlertComponent.erro(
                    "Erro ao salvar barbeiro."
            );
        }
    }

    public void atualizarTabela(
            TableView<Barbeiro> tabela
    ) {

        tabela.setItems(
                FXCollections.observableArrayList(
                        barbeiroDAO.readAll()
                )
        );
    }
}
package org.ifgoiano.barbearia.view.cliente;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.view.components.AlertComponent;

public class ClienteController {

    private final ClienteDAO clienteDAO =
            new ClienteDAO();

    public void salvar(
            String nome,
            String telefone,
            String email,
            TableView<Cliente> tabela
    ) {

        try {

            Cliente cliente = new Cliente();

            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);

            boolean sucesso =
                    clienteDAO.create(cliente);

            if (sucesso) {

                atualizarTabela(tabela);

                AlertComponent.sucesso(
                        "Cliente salvo com sucesso."
                );

            } else {

                AlertComponent.erro(
                        "Falha ao salvar cliente."
                );
            }

        } catch (Exception e) {

            AlertComponent.erro(
                    "Erro ao salvar cliente."
            );
        }
    }

    public void atualizarTabela(
            TableView<Cliente> tabela
    ) {

        tabela.setItems(
                FXCollections.observableArrayList(
                        clienteDAO.readAll()
                )
        );
    }
}
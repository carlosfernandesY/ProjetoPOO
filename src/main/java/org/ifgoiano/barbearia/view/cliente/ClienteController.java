package org.ifgoiano.barbearia.view.cliente;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.service.ClienteService;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.view.components.AlertComponent;

public class ClienteController {

    private final ClienteService clienteService = new ClienteService(new ClienteDAO());

    public void salvar(String nome, String telefone, String email, TableView<Cliente> tabela) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);

            clienteService.createCliente(cliente);

            atualizarTabela(tabela);
            AlertComponent.sucesso("Cliente salvo com sucesso.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            AlertComponent.aviso(e.getMessage());
        } catch (Exception e) {
            AlertComponent.erro("Erro ao salvar cliente no banco de dados.");
        }
    }

    public void atualizarTabela(TableView<Cliente> tabela) {
        tabela.setItems(
                FXCollections.observableArrayList(
                        clienteService.readAllCliente()
                )
        );
    }
}
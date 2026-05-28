package org.ifgoiano.barbearia.view.barbeiro;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.dao.BarbeiroDAO;
import org.ifgoiano.barbearia.service.BarbeiroService;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.view.components.AlertComponent;

public class BarbeiroController {

    private final BarbeiroService barbeiroService = new BarbeiroService(new BarbeiroDAO());

    public void salvar(String nome, TableView<Barbeiro> tabela) {
        try {
            Barbeiro barbeiro = new Barbeiro();
            barbeiro.setNome(nome);
            barbeiroService.createBarbeiro(barbeiro);

            atualizarTabela(tabela);
            AlertComponent.sucesso("Barbeiro salvo com sucesso.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            AlertComponent.aviso(e.getMessage());
        } catch (Exception e) {
            AlertComponent.erro("Falha ao salvar barbeiro no banco de dados.");
        }
    }

    public void atualizarTabela(TableView<Barbeiro> tabela) {
        tabela.setItems(
                FXCollections.observableArrayList(
                        barbeiroService.readAllBarbeiro()
                )
        );
    }
}
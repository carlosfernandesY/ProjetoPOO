package org.ifgoiano.barbearia.view.atendimento;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import org.ifgoiano.barbearia.model.Atendimento;
import org.ifgoiano.barbearia.model.Barbeiro;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.AtendimentoService;
import org.ifgoiano.barbearia.view.components.AlertComponent;

import java.sql.Date;
import java.time.LocalDate;

public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    public void salvar(Cliente cliente, Barbeiro barbeiro, String valor, LocalDate data, TableView<Atendimento> tabela) {
        try {
            // Conversão de dados da tela para os tipos do Java/Banco
            double valorFormatado = Double.parseDouble(valor.replace(",", "."));
            Date dataSql = (data != null) ? Date.valueOf(data) : null;

            Atendimento atendimento = new Atendimento();
            atendimento.setCliente(cliente);
            atendimento.setBarbeiro(barbeiro);
            atendimento.setValorTotal(valorFormatado);
            atendimento.setData(dataSql);

            // O Service faz a validação e salva. Se falhar, lança Exception.
            atendimentoService.createAtendimento(atendimento);
            
            atualizarTabela(tabela);
            AlertComponent.sucesso("Atendimento salvo com sucesso.");

        } catch (NumberFormatException e) {
            AlertComponent.aviso("O valor digitado é inválido. Use apenas números.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Captura as regras do AtendimentoService (Ex: "O barbeiro é obrigatório")
            AlertComponent.aviso(e.getMessage());
        } catch (Exception e) {
            AlertComponent.erro("Erro ao salvar atendimento.");
        }
    }

    public void atualizarTabela(TableView<Atendimento> tabela) {
        tabela.setItems(FXCollections.observableArrayList(atendimentoService.readAllAtendimento()));
    }
}
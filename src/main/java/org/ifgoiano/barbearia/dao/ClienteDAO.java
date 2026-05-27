package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.connection.ConnectionFactory;
import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements EntidadeDAO<Cliente> {
    Connection connection;

    public ClienteDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean create(Cliente object) {
        String sql = "INSERT INTO Cliente (nome,email,telefone) VALUES (?,?,?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setString(2, object.getEmail());
            preparedStatement.setString(3, object.getTelefone());
            return  preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Cliente readById(Integer id) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?;";
        Cliente cliente = new Cliente();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cliente.setIdCliente(resultSet.getInt("idCliente"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setEmail(resultSet.getString("email"));
            return cliente;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(Cliente object) {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, object.getIdCliente());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateById(Cliente object) {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, telefone = ? WHERE idCliente = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setString(2, object.getEmail());
            preparedStatement.setString(3, object.getTelefone());
            preparedStatement.setInt(4, object.getIdCliente());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /*
    ----------------------------------------------------
    READ ALL

    Busca todos os clientes cadastrados.

    Retorno:
    - Lista contendo todos os clientes
    ----------------------------------------------------
    */
    @Override
    public List<Cliente> readAll() {
        String sql = "SELECT * FROM Cliente;";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone")); // Adicionado o telefone que faltava no readById

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return clientes;
    }
}

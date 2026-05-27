package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.connection.ConnectionFactory;
import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            return  preparedStatement.executeUpdate() > 0;
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
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void updateById(Cliente object) {
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

    }

}

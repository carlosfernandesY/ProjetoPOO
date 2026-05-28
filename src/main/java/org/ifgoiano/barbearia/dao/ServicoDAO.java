package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO implements EntidadeDAO<Servico> {

    Connection connection;

    public ServicoDAO(Connection connection) {
        this.connection = connection;

    }

    @Override
    public boolean create(Servico object) {
        String sql = "INSERT INTO Servicos (nome,preco,descricao) VALUES(?,?,?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setDouble(2, object.getPreco());
            preparedStatement.setString(3, object.getDescricao());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Servico readById(Integer id) {
        String sql = "SELECT * FROM Servicos WHERE idServico = ?;";
        Servico servico = new Servico();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            servico.setIdServico(rs.getInt("idCliente"));
            servico.setNome(rs.getString("nome"));
            servico.setPreco(rs.getDouble("preco"));
            servico.setDescricao(rs.getString("descricao"));
            return servico;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateById(Servico object) {
        String sql = "UPDATE Servico SET nome = ?, preco = ?, descricao = ? WHERE idServico = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setDouble(2, object.getPreco());
            preparedStatement.setString(3, object.getDescricao());
            preparedStatement.setInt(4, object.getIdServico());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(Servico object) {
        String sql = "DELETE FROM Servico WHERE idServico = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, object.getIdServico());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public List<Servico> readAll() {
        String sql = "SELECT * FROM Servico;";
        List<Servico> servicos = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Servico servico = new Servico();
                servico.setIdServico(resultSet.getInt("idServico"));
                servico.setNome(resultSet.getString("nome"));
                servico.setPreco(resultSet.getDouble("preco"));
                servico.setDescricao(resultSet.getString("descricao"));
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return servicos;
    }
}


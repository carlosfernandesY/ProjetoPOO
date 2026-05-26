package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.connection.ConnectionFactory;
import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Atendimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendimentDAO implements EntidadeDAO<Atendimento> {
    Connection connection;
    public AtendimentDAO(Connection connection) {
        this.connection = new ConnectionFactory().getConnection();
    }
    @Override
    public void create(Atendimento object) {
        String sql = "INSERT INTO Atendimento(idClienteFK,idBarbeiro,data,valorTotal) VALUES(?, ?, ?, ?);";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1,object.getCliente().getIdCliente());
            preparedStatement.setInt(2,object.getBarbeiro().getIdBarbeiro());
            preparedStatement.setDate(3,object.getData());
            preparedStatement.setDouble(4,object.getValorTotal());
            preparedStatement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Atendimento readById(Integer id) {
        String sql = "SELECT * FROM Atendimento WHERE idAtendimento = ?;";
        Atendimento atendimento = null;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            atendimento = new Atendimento();
            atendimento.setIdAtendimento(resultSet.getInt("idAtendimento"));
            atendimento.setData(resultSet.getDate("data"));
            atendimento.setValorTotal(resultSet.getDouble("valorTotal"));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return atendimento;
    }

    @Override
    public boolean deleteById(Atendimento atendimento) {
        String sql = "DELET FROM Atendiente WHERE idAtendimento = ?;";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1,atendimento.getIdAtendimento());
            preparedStatement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void updateById(Atendimento object) {
        String  sql = "UPDATE Atendimento SET data = ?, valorTotal = ? WHERE idAtendimento = ?;";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setDate(1,object.getData());
            preparedStatement.setDouble(2,object.getValorTotal());
            preparedStatement.setInt(3,object.getIdAtendimento());
            preparedStatement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}

package org.ifgoiano.dao;

import com.sun.jdi.connect.spi.Connection;
import org.ifgoiano.connection.ConnectionFactory;
import org.ifgoiano.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.model.Barbeiro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarbeiroDAO implements EntidadeDAO<Barbeiro> {

    Connection connection;

    public BarbeiroDAO(){
        this.connection = (Connection) new ConnectionFactory().getConnection();
    }
    @Override
    public void create(Barbeiro object) {
        String sql = "INSERT INTO Barbeiro (nome) VALUES (?);";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setString(1,object.getNome());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Barbeiro readById(Integer id) {
        String sql = "SELECT * FROM Barbeiro wHERE idBarbeiro = ?;";
        Barbeiro barbeiro = new Barbeiro();

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            barbeiro = new Barbeiro();
            barbeiro.setIdBarbeiro(rs.getInt("idBarbeiro"));
            barbeiro.setNome(rs.getString("nome"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return barbeiro;
    }

    @Override
    public boolean deleteById(Barbeiro object) {
        String sql = "DELETE FROM Barbeiro wHERE idBarbeiro = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement .setInt(1, object.getIdBarbeiro());
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public void updateById(Barbeiro object) {
        String sql = "UPDATE Barbeiro SET nome = ? wHERE idBarbeiro = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setInt(2, object.getIdBarbeiro());
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}

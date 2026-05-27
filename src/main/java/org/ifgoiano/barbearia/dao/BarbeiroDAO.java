package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.connection.ConnectionFactory;
import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Barbeiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class BarbeiroDAO implements EntidadeDAO<Barbeiro> {

    Connection connection;

    public BarbeiroDAO() {
        this.connection = (Connection) new ConnectionFactory().getConnection();
    }

    @Override
    public boolean create(Barbeiro object) {
        String sql = "INSERT INTO Barbeiro (nome) VALUES (?);";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Barbeiro readById(Integer id) {
        String sql = "SELECT * FROM Barbeiro wHERE idBarbeiroFK = ?;";
        Barbeiro barbeiro = new Barbeiro();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            barbeiro = new Barbeiro();
            barbeiro.setIdBarbeiro(rs.getInt("idBarbeiroFK"));
            barbeiro.setNome(rs.getString("nome"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return barbeiro;
    }

    @Override
    public boolean deleteById(Barbeiro object) {
<<<<<<< Updated upstream
        String sql = "DELETE FROM Barbeiro wHERE idBarbeiro = ?;";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement .setInt(1, object.getIdBarbeiro());
           return  preparedStatement.executeUpdate() > 0;
=======
        String sql = "DELETE FROM Barbeiro wHERE idBarbeiroFK = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, object.getIdBarbeiro());
            preparedStatement.execute();
>>>>>>> Stashed changes

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;

        return false;
    }

    @Override
    public List<Barbeiro> readAll() {
        String sql = "SELECT * FROM Barbeiro;";
        List<Barbeiro> barbeiros = new java.util.ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Barbeiro barbeiro = new Barbeiro();
                barbeiro.setIdBarbeiro(rs.getInt("idBarbeiroFK"));
                barbeiro.setNome(rs.getString("nome"));
                barbeiros.add(barbeiro);
            }
        } catch (SQLException e) {
            System.out.println("Erro Barbeiro: " + e.getMessage());
        }
        return barbeiros;
    }

    @Override
    public boolean updateById(Barbeiro object) {
        String sql = "UPDATE Barbeiro SET nome = ? wHERE idBarbeiro = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getNome());
            preparedStatement.setInt(2, object.getIdBarbeiro());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}

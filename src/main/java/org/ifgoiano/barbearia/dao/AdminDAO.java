package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AdminDAO implements EntidadeDAO<Admin> {
    Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Admin admin) {
        String sql = "INSERT INTO admin (login,senha) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getLogin());
            preparedStatement.setString(2, admin.getSenha());
            return preparedStatement.executeUpdate() > 0;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Admin readById(Integer id) {
        String sql = "SELECT * FROM admin WHERE idAdmin = ?;";
        Admin admin = new Admin();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            admin.setIdAdmin(resultSet.getInt("idAdmin"));
            admin.setLogin(resultSet.getString("login"));
            admin.setSenha(resultSet.getString("senha"));
            return admin;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateById(Admin object) {
        String sql = "UPDATE admin SET login=?, senha=? WHERE idAdmin=?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setString(2, object.getSenha());
            preparedStatement.setInt(3, object.getIdAdmin());
            return preparedStatement.executeUpdate() > 0 ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(Admin object) {
        String sql = "DELETE FROM admin WHERE idAdmin=?;";
        try(PreparedStatement  preparedStatement = this.connection.prepareStatement(sql)){
            preparedStatement.setInt(1,object.getIdAdmin());
           return  preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public List<Admin> readAll() {
        return List.of();
    }
}



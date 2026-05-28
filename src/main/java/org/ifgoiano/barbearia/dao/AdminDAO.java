package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    Connection  connection;
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean create(Admin admin) {
        String sql = "INSERT INTO admin (login,senha) VALUES (?,?)";
        try(PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,admin.getLogin());
            preparedStatement.setString(2,admin.getSenha());
            repreparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

package org.ifgoiano.barbearia.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  Connection connection;
  private final static String url  = "jdbc:mysql://localhost:3306/barbearia";
  private final static String username = "fernandes";
  private final static String password= "password";

  public Connection getConnection(){
      try{
          return DriverManager.getConnection(url,username,password);
      }catch(SQLException e){
          System.out.println("Erro ao se connectar");
          System.out.println(e.getMessage());
      }
      return null;
  }
}


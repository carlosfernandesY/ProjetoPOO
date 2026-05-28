package org.ifgoiano.barbearia.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ========================================================
 * CLASSE: ConnectionFactory
 * RESPONSABILIDADE: Gerenciar conexões com o banco.
 * ========================================================
 */
public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/barbearia";
    private static final String USER = "fernandes";
    private static final String PASSWORD = "password";

    public Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (SQLException e) {

            System.out.println("Erro ao conectar no banco");
            e.printStackTrace();

            return null;
        }
    }
}
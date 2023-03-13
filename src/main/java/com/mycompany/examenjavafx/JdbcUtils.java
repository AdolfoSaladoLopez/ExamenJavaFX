package com.mycompany.examenjavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcUtils {
    static private Connection con = null;

    static {
        String url = "jdbc:mysql://localhost:3306/examen";
        String user = "root";
        String password = "12345";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Satisfactoria");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    static Connection getConnection() {
        return con;
    }
}

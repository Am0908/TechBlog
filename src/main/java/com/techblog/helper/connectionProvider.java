package com.techblog.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionProvider extends com.mysql.cj.jdbc.Driver {
    private static Connection con;

    public connectionProvider() throws SQLException {
    }

    public static Connection connectionProvider() throws SQLException {
        try {
            if (con == null) {
                // driver class load
                Class.forName("com.mysql.cj.jdbc.Driver");
//                Class.forName("org.sqlite.JDBC");

                // creating connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechBlog", "root", "SKch1234@");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}


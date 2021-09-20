package com.company.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public static Connection Creatconnection() {
        Connection conn = null;
        String url = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=MTK1";
        String user = "sa";
        String password = "123";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return conn;
    }
}

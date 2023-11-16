package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection(){
        Connection con = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/resources/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            Class.forName(driver);
            con = DriverManager.getConnection(url, prop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset){
        try {
            rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

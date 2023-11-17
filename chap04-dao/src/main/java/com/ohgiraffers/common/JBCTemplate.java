package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JBCTemplate {

    private static Connection con;
    public static Connection getConnection(){ // 싱글톤 패턴(소스를 아끼기 위해 하나의 주소를 반복적으로 사용)
        Properties prop = new Properties();

        if(con == null){
            try {
                prop.load(new FileReader("src/main/resources/config/connection-info.properties"));
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                Class.forName(driver);

                con = DriverManager.getConnection(url, prop);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return con;
    }

    public static Connection getConnection2(){
        Connection con2 = null;
        Properties prop = new Properties();


        try {
            prop.load(new FileReader("src/main/resources/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            Class.forName(driver);

            con2 = DriverManager.getConnection(url, prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con2;
    }

    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset){
        try {
            rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

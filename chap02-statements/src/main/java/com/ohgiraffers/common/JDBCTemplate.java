package com.ohgiraffers.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection(){
        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            con = DriverManager.getConnection(url, prop);
        } catch (FileNotFoundException e) { // FileNotFoundException이 IOException에 포함되기는 하나, 정확한 에러 사유를 알기 위해 나눈다. 경로의 문제
            e.printStackTrace();
        } catch (IOException e) { // 경로나 내부 정보에 문제가 있음
            e.printStackTrace();
        } catch(SQLException e){ // 연결의 문제
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

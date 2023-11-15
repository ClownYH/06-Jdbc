package com.ohgiraffers.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        } catch (FileNotFoundException e) { // 파일이 없을 때
            e.printStackTrace();
        } catch (IOException e) { // 읽어올 수 없을 때
            throw new RuntimeException(e);
        } catch (SQLException e) { // SQL
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

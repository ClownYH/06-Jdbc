package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {

    public static void main(String[] args){
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url"); // db 접속 경로
            String user = prop.getProperty("user"); // db id
            String password = prop.getProperty("password"); // id의 비밀번호
            // 보안을 위해 파일을 따로 저장해서 이런 식으로 연결한다.
            // DriverManager.getConnection은 매개변수로 url, user(db id), password(id의 비밀번호)를 전달해주면
            // 연결하려고 하는 driver를 찾아서 해당 db 서버의 데이터 베이스에 연결하는 메소드이다.
            con = DriverManager.getConnection(url, user, password);
            System.out.println(con);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                con.close(); // 커낵션 개체가 존재하는지 확신할 수 없기 때문에 try/catch로 잡아준다.
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

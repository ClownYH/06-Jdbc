package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;
public class Application01 {

    public static void main(String[] args){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1, "손가락맛 순대국밥"); // 인덱스는 ? 순서대로
            pstmt.setInt(2, 8000);
            pstmt.setInt(3, 4);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate(); // 완료가 되면 1, 안되면 0으로 출력된다.
            System.out.println("결과 " + result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (InvalidPropertiesFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);

            // finally에 있는 것은 try 이후 반드시 실행하는 것인데 close가 들어가기 때문에 try 안에 conection 등을 넣을 시 간섭이 불가하여 닫을 개체가 없기 때문에 에러가 난다.
        }

    }
}

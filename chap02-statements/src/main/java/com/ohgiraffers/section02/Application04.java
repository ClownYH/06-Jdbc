package com.ohgiraffers.section02;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO emp = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("성 씨를 입력해주세요.");
        String empName = scanner.nextLine();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml")); // stream 객체를 가져옴
            pstmt = con.prepareStatement(prop.getProperty("selectEmpByFamilyName"));
            pstmt.setString(1, empName);
            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println(rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }
}

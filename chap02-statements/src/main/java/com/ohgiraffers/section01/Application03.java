package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application03 {

    public static void main(String[] args){
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        Scanner scanner = new Scanner(System.in);

        try {
            stmt = con.createStatement();
            System.out.println("조회하고자 하는 이름을 입력해주세요.");
            String empName = scanner.nextLine();
            String query = "SELECT " +
                                "EMP_ID, " +
                                "EMP_NAME" +
                                " FROM EMPLOYEE" +
                                " WHERE EMP_NAME = '" + empName + "'";
            rset = stmt.executeQuery(query);
            while(rset.next()){
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(stmt);
            close(rset);
            scanner.close();
        }
    }
}

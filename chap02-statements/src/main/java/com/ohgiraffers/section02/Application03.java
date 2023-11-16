package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("조회할 성씨를 입력해주세요 : ");
        String first = scanner.nextLine();
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, first); // 컬럼의 인덱스 값과 ?에 들어갈 값
            System.out.println(pstmt);


            rset = pstmt.executeQuery();
            while(rset.next()){
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(pstmt);
        }


    }
}

package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {

    public static void main(String[] args){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? ");
            pstmt.setString(1, "200"); // 가져올 컬럼의 인덱스와 ?에 들어갈 값
//            pstmt.setInt(1, 200);

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
            close(pstmt); // Statement를 상속받기 때문에 close 해줘야 한다.
        }


    }
}

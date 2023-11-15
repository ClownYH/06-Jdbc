package com.ohgiraffers.section01;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.*;
public class Application02 {

    public static void main(String[] args){
        // 사원 번호에 해당하는 사원의 정보를 조회한다.
        // 1. connection DB
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {
            // 2. 쿼리를 만들어야 한다. Statement
            stmt = con.createStatement();

            String empid = "200";
            // 3. 쿼리를 보낸다.
            // 4. 결과를 받는다. ResultSet
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID =" + empid);
            while (rset.next()){
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(con);
            close(stmt); // 닫는 순서는 상관이 없다.
        }




    }
}

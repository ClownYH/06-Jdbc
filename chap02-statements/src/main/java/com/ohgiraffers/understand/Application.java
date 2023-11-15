package com.ohgiraffers.understand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {

    public static void main(String[] args){
        // 1. db 연결
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        try {
            // 2. 쿼리 생성
            stmt = con.createStatement();
            // 3. 쿼리 보내고 응답받기
            // 3-1. 급여를 가장 많이 받는 사람
            System.out.println("급여를 가장 많이 받는 사원(사번, 이름, 연락처)");

            rset = stmt.executeQuery("SELECT EMP_NO," +
                    "                            EMP_NAME," +
                    "                            PHONE" +
                    "                     FROM EMPLOYEE" +
                    "                     ORDER BY SALARY DESC" +
                    "                     LIMIT 1;");
            while(rset.next()) {
                System.out.println(rset.getString("EMP_NO") +
                            " " + rset.getString("EMP_NAME") +
                            " " + rset.getString("PHONE"));
            }
            System.out.println();
            // 3-2. 선동일 사원의 사번, 이름, 연락처, 직급
            System.out.println("선동일 사원의 사번, 이름, 연락처, 직급");
            rset = stmt.executeQuery("SELECT EMP_NO," +
                    "                            EMP_NAME," +
                    "                            PHONE," +
                    "                            JOB_NAME" +
                    "                     FROM EMPLOYEE" +
                    "                     JOIN JOB ON JOB.JOB_CODE = EMPLOYEE.JOB_CODE" +
                    "                     WHERE EMP_NAME = '선동일'");
            while(rset.next()){
                System.out.println(rset.getString("EMP_NO") +
                            " " + rset.getString("EMP_NAME") +
                            " " + rset.getString("PHONE") +
                            " " + rset.getString("JOB_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(stmt);
            close(con);
        }

    }
}

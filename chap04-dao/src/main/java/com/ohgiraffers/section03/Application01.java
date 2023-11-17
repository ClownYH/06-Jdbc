package com.ohgiraffers.section03;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.common.JBCTemplate.*;
public class Application01 {
    public static void main(String[] args){
        /* transaction */
        Connection con = getConnection();

        try {
            con.setAutoCommit(false); // 오토 커밋 설정 해제

//            insert1 -> 오류가 나면
//            insert2
//            insert3

            con.commit();
            System.out.println("autoCommit의 현재의 값 : " + con.getAutoCommit());
        } catch (SQLException e) {
            try {
                con.rollback(); // 오류를 받아 롤백
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

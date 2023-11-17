package com.ohgiraffers.section01;

import java.sql.Connection;

import static com.ohgiraffers.common.JBCTemplate.*;

/**
 * 싱글톤 패턴 확인
 */
public class Singleton {

    public static void main(String[] args){

        Connection con = getConnection();

        Connection con2 = getConnection();

        System.out.println(con);
        System.out.println(con2);

        Connection con3 = getConnection2();
        Connection con4 = getConnection2();
        System.out.println("============================");
        System.out.println(con3);
        System.out.println(con4);


    }
}

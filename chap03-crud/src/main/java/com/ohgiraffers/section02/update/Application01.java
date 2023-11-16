package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;
public class Application01 {

    public static void main(String[] args){

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        System.out.println("메뉴를 수정합니다.");
        System.out.println("바꾸고자 하는 메뉴의 이름을 입력해주세요.");
        String columnName = scanner.nextLine();
        System.out.println("바꾸고자 하는 이름을 입력해주세요.");
        String name = scanner.nextLine();
        System.out.println("바꿀 가격을 입력해주세요.");
        int price = scanner.nextInt();
        System.out.println("판매 상태를 입력해주세요.(y/n)");
        String order = scanner.next();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setString(3,order);
            pstmt.setString(4, columnName);

            result = pstmt.executeUpdate();

            if(result > 0 ){
                System.out.println("업데이트 되었습니다.");
            }else {
                System.out.println("다시 시도해주세요.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }


    }
}

package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;
public class Application02 {

    public static void main(String[] args){
        /*
        * 사용자가 메뉴를 동록할 수 있도록 만들어주세요.
        * 등록이 완료되면 메뉴 등록 성공, 실패하면 다시 등록을 요청해주세요.
        * */
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        Scanner scanner = new Scanner(System.in);

        int result = 0;
        System.out.println("메뉴를 등록합니다.");
        System.out.println("메뉴 이름을 정해주세요.");
        String menuName = scanner.nextLine();
        System.out.println("메뉴 가격을 정해주세요.");
        int menuPrice = scanner.nextInt();
        System.out.println("메뉴 분류를 정해주세요.(4 : 한식, 5 : 중식, 6 : 일식, 7 : 퓨전)");
        int menuCategory = scanner.nextInt();
        System.out.println("메뉴는 주문이 가능한가요?(y/n)");
        String orderable = scanner.next();
        System.out.println();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));


            pstmt.setString(1, menuName);
            pstmt.setInt(2, menuPrice);
            pstmt.setInt(3, menuCategory);
            pstmt.setString(4,orderable);

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("메뉴 등록 성공");
            }else {
                System.out.println("다시 등록해주세요.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(pstmt);
        }
    }
}

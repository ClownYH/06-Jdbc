package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JBCTemplate.*;
public class Application {
    public static void main(String[] args){
        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset1 = null;
        ResultSet rset2 = null;

        int result = 0;
        int maxMenuCode = 0;
        List<Map<Integer, String>> categoryList = null;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query1 = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String insert = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query1);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(insert);

            rset1 = pstmt1.executeQuery();
            if(rset1.next()){
                maxMenuCode = rset1.getInt("MAX(A.MENU_CODE)");
            }
            System.out.println("maxMenuCode : " + maxMenuCode);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while(rset2.next()){
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }

            System.out.println("categoryList : " + categoryList);

            Scanner scanner = new Scanner(System.in);
            System.out.println("등록할 메뉴의 이름을 입력하세요 :");
            String menuName = scanner.nextLine();
            System.out.println("신규 메뉴의 가격을 입력해주세요 :");
            int price = scanner.nextInt();
            System.out.println("카테고리 번호를 입력해주세요 :");
            String categoryName = scanner.next();
            System.out.println("판매 여부를 선택해주세요 :");
            String status = scanner.next();

            pstmt3.setString(1, menuName);
            pstmt3.setInt(2, price);
            pstmt3.setString(3, categoryName);
            pstmt3.setString(4, status);

            result = pstmt3.executeUpdate();
            if(result > 0){
                System.out.println("메뉴 등록 성공");
            }else{
                System.out.println("메뉴 등록 실패");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt1);
            close(pstmt2);
            close(pstmt3);
            close(rset1);
            close(rset2);
        }


    }
}

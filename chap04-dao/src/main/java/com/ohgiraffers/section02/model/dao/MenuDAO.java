package com.ohgiraffers.section02.model.dao;

import com.ohgiraffers.section02.model.DTO.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.ohgiraffers.common.JBCTemplate.*;
public class MenuDAO { // DateAccessObject

    private Properties prop = new Properties();

    public MenuDAO(String url) { // 만약 비어있는 기본 생성자를 쓴다면 실행은 되겠지만 그 이후 연결될 인스턴스들에서 에러가 날 것이다.
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int selectLastMenuCode(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if(rset.next()){
                maxCode = rset.getInt("MAX(A.MENU_CODE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }
        return maxCode;

    }

    public List<Map<Integer, String>> selectAllCategory(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        List<Map<Integer, String>> categoryList = null;
        String query = prop.getProperty("selectAllCategoryList");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if(rset.next()){ // if냐 while이냐는 동작에 문제 없으나 메모리 낭비를 하냐 안하냐 이다
                Map<Integer, String> category = new HashMap<>();

                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME")); // 키, 밸류
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }
        return categoryList;
    }

    public int insertMenu(Connection con, MenuDTO menuDTO){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setString(3, menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getStatus());
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
        return result;
    }
}

package com.ohgiraffers.restaurant.model.dao;

import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.ohgiraffers.restaurant.common.JDBCTemplate.*;

public class MenuDAO { // service에서 받은 값을 통해 데이터베이스에 쿼리를 전달해 명령을 수행한 후 해당 동작에 대한 성공 여부를 값으로 치환해 반환한다.
    Properties prop = new Properties();

    public MenuDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MenuVO> findAllMenu(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        List<MenuVO> resultList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(prop.getProperty("findAllMenu"));

            while(rset.next()){
                resultList.add(new MenuVO(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4), rset.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(stmt);
        }
        return resultList;
    }

    /**
     * 카테고리 코드가 존재하는지 확인한다.
     * @param con Connection
     * @return List<Integer>
     */
    public List<String> findAllCategoryCode(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<String> resultList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(prop.getProperty("findAllCategory"));

            while(rset.next()){
                resultList.add(rset.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rset);
            close(stmt);
        }
        return resultList;
    }

    public int registMenu(Connection con, MenuDTO menuDTO) {

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2,menuDTO.getPrice());
            pstmt.setString(3, menuDTO.getCategory());
            pstmt.setString(4, menuDTO.getStatus());

            result = pstmt.executeUpdate();

            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int targetMenu(Connection con, String menuName, MenuDTO menuDTO){
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("modifyMenu"));
            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setString(3, menuDTO.getCategory());
            pstmt.setString(4, menuDTO.getStatus());
            pstmt.setString(5, menuName);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteMenu(Connection con, MenuDTO menuDTO){
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));
            pstmt.setString(1, menuDTO.getMenuName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
    }
}

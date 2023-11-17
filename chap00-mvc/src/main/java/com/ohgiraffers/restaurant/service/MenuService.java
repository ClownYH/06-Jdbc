package com.ohgiraffers.restaurant.service;

import com.ohgiraffers.restaurant.model.dao.MenuDAO;
import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.restaurant.common.JDBCTemplate.*;
public class MenuService { // 컨트롤에서 전달받은 값과 데이터베이스 연결 시도를 DAO로 전달해 결과를 도출한 후 다시 컨트롤로 반환한다.

    private MenuDAO menuDAO;

    public MenuService(String url) {
        this.menuDAO = new MenuDAO(url);
    }

    public List<MenuVO> findMenu(){
        // DB 연결
        Connection con = getConnection();
        // business logic Ex) price > 0
        List<MenuVO> list = menuDAO.findAllMenu(con);

        close(con);
        return list;
    }

    public int registMenu(MenuDTO menuDTO){
        Connection con = getConnection();
        List<String> category = menuDAO.findAllCategoryCode(con);

        if(!category.contains(menuDTO.getCategory())){
            return 0;
        }

        if(menuDTO.getPrice() <= 1000){
            return 0;
        }

        int result = menuDAO.registMenu(con, menuDTO);

        close(con);

        return result;
    }

    public int modifyMenu(String menuName, MenuDTO menuDTO){
        Connection con = getConnection();
        int modifiedMenu = menuDAO.targetMenu(con, menuName, menuDTO);

        return modifiedMenu;
    }

    public int deleteMenu(MenuDTO menuDTO){
        Connection con = getConnection();
        int result = menuDAO.deleteMenu(con, menuDTO);

        return result;
    }
}

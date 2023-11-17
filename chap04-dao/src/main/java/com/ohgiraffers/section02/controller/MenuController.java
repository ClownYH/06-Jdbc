package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.model.dao.MenuDAO;

import static com.ohgiraffers.common.JBCTemplate.*;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){
        int result = menuDAO.selectLastMenuCode(getConnection());
        System.out.println(result);
    }
}

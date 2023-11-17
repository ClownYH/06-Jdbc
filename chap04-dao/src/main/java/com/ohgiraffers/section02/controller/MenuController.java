package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.model.DTO.MenuDTO;
import com.ohgiraffers.section02.model.dao.MenuDAO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JBCTemplate.*;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){
        int result = menuDAO.selectLastMenuCode(getConnection());
        System.out.println(result);
    }

    public void findCategory(){
        List<Map<Integer, String>> allCategory = menuDAO.selectAllCategory(getConnection());
        for (Map<Integer, String> category : allCategory){
            System.out.println(category);
        }
    }

    public void insertMenu(){
        MenuDTO menuDTO = new MenuDTO();
        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 이름을 입력해주세요.");
        menuDTO.menuName(sc.nextLine());
        System.out.println("메뉴 가격을 입력해주세요.");
        menuDTO.price(sc.nextInt());
        sc.nextLine();
        System.out.println("카테고리 번호를 입력해주세요.");
        menuDTO.categoryCode(sc.nextLine());
        System.out.println("상태 코드를 입력해주세요.");
        menuDTO.status(sc.nextLine());

        int result = menuDAO.insertMenu(getConnection(), menuDTO);
        if(result > 0){
            System.out.println("메뉴 등록 성공");
        }else{
            System.out.println("메뉴 등록 실패");
        }
    }
}

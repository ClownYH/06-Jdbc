package com.ohgiraffers.restaurant.controller;

import com.ohgiraffers.restaurant.model.dto.MenuDTO;
import com.ohgiraffers.restaurant.model.vo.MenuVO;
import com.ohgiraffers.restaurant.service.MenuService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
* 사용자의 요청을 받아
* 유효성 체크를 하고 요청을 service로 전달하고
* 이후 service의 반환 값을 페이지로 반환한다.
* */
public class MenuCtr {

    private MenuService menuService;

    public MenuCtr(MenuService menuService) { // DI(Dependency Injection) 추후에는 스프링에서 어느 정도 알아서 해주지만 개념은 알아두는 편이 좋다.
        this.menuService = menuService;
    }

    public List<MenuVO> findAllMenu(){
        // 값을 확인한다.
        List<MenuVO> list = menuService.findMenu();

        // 반환값 확인 후 응답 값을 지정
        if(Objects.isNull(list)){
            return null;
        }

        return list;
    }

    public String modifyMenu(String menuName, MenuDTO menuDTO){
        if(Objects.isNull(menuDTO)){
            return "변경할 내용이 없습니다.";
        }

        if(menuDTO.getMenuName() == null || menuDTO.getMenuName().equals("")){
            return " 변경할 메뉴 이름을 등록해주세요.";
        }

        if(menuDTO.getPrice() <= 0){
            return "변경할 메뉴 가격은 음수일 수 없습니다.";
        }

        if(menuDTO.getCategory() == null ||menuDTO.getCategory().equals("")){
            return "변경할 카테고리는 필수!";
        }

        if(menuDTO.getStatus() == null ||menuDTO.getStatus().equals("")){
            return "변경할 판매여부를 등록해주세요.";
        }

        // 유효성 검사 종료 후
        int result = menuService.modifyMenu(menuName, menuDTO);

        if(result == 0){
            return "잘못된 변경입니다. 다시 시도해주세요.";
        }

        return "메뉴가 변경되었습니다.";
    }

    public String registMenu(MenuDTO menuDTO){
        if(Objects.isNull(menuDTO)){
            System.out.println("메뉴가 없네요.");
            return "메뉴 정보가 존재하지 않아요. 입력 좀 해주세요.";
        }

        if(menuDTO.getMenuName() == null || menuDTO.getMenuName().equals("")){
            return "메뉴 이름을 등록해주세요.";
        }

        if(menuDTO.getPrice() <= 0){
            return "메뉴 가격은 음수일 수 없습니다.";
        }

        if(menuDTO.getCategory() == null ||menuDTO.getCategory().equals("")){
            return "카테고리는 필수!";
        }

        if(menuDTO.getStatus() == null ||menuDTO.getStatus().equals("")){
            return "판매여부를 등록해주세요.";
        }

        // 유효성 검사가 끝난 메뉴
        int result = menuService.registMenu(menuDTO);

        if(result <= 0){
            return "등록 중 오류가 발생했습니다.";
        }
        return "등록이 완료되었습니다.";
    }

    public String deleteMenu(MenuDTO menuDTO) { // 유효성을 어떻게 검사하지?
        if(Objects.isNull(menuDTO) || menuDTO.getMenuName().equals("")){
            return "삭제할 대상의 이름이 입력되지 않았습니다.";
        }
        int result = menuService.deleteMenu(menuDTO);

        if(result < 0){
            return "삭제되지 않았습니다.";
        }

        return "정상적으로 삭제되었습니다.";
    }
}



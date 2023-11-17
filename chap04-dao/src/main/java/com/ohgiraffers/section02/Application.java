package com.ohgiraffers.section02;

import com.ohgiraffers.section02.controller.MenuController;

import java.util.Scanner;

public class Application {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        MenuController menu = new MenuController();
        loop : while(true){
            System.out.println("사용할 기능을 선택해주세요.");
            System.out.println("1번 가장 최신 메뉴");
            System.out.println("2번 모든 카테고리 목록 조회");
            System.out.println("3번 메뉴 등록");
            System.out.println("9번 프로그램 종료");
            int choice = scanner.nextInt();
            switch(choice){
                case 1 : menu.findMaxCode(); break;
                case 2 : menu.findCategory(); break;
                case 3 : menu.insertMenu(); break;
                default: break loop;
            }
        }



    }
}

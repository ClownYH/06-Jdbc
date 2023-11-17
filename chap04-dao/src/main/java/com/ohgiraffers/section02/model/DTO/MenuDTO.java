package com.ohgiraffers.section02.model.DTO;

import java.awt.*;

public class MenuDTO {

    private String menuName;
    private int price;
    private String categoryCode;
    private String status;

    public MenuDTO(String menuName, int price, String categoryCode, String status) {
        this.menuName = menuName;
        this.price = price;
        this.categoryCode = categoryCode;
        this.status = status;
    }

    public MenuDTO menuName(String name){
        this.menuName = name;
        return this;
    }

    public MenuDTO price(int price){
        if(price <= 0){
            System.out.println("돌아갈까봐 그래~~~");
            return this;
        }else {
            this.price = price;
        }
        return this;
    }

    public MenuDTO categoryCode(String code){
        this.categoryCode = code;
        return this;
    }

    public MenuDTO status(String status){
        if(status.equals("예") || status.equals("Y")){
            this.status = "Y";
        }else if(status.equals("아니오") || status.equals("N")){
            this.status = "N";
        }else {
            System.out.println("돌아갈까봐 그래~~~~~~");
        }
        return this;
    }

    public MenuDTO builder(){
        return new MenuDTO(menuName, price, categoryCode, status);
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + menuName + '\'' +
                ", price=" + price +
                ", categoryCode='" + categoryCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

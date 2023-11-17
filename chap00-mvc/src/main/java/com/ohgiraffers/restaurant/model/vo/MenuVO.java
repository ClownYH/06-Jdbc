package com.ohgiraffers.restaurant.model.vo;

public class MenuVO {

    private int menuCode;

    private String menuName;

    private int price;

    private String category;

    private String status;

    public MenuVO() {
    }

    public MenuVO(int menuCode, String menuName, int price, String category, String status) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

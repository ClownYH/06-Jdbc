package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {

    public static void main(String[] args){

        Connection con = getConnection();
        PreparedStatement psmt = null;
        Properties prop = new Properties();
        Scanner scanner = new Scanner(System.in);
        int result = 0;

        System.out.println("삭제하고자 하는 메뉴의 코드를 입력해주세요.");
        int menuNo = scanner.nextInt();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            psmt = con.prepareStatement(prop.getProperty("deleteMenu"));
            psmt.setInt(1, menuNo);

            result = psmt.executeUpdate();
            if(result > 0){
                System.out.println("메뉴가 삭제되었습니다.");
            }else {
                System.out.println("다시 시도해주세요.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

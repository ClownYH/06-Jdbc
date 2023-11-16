package com.ohgiraffers.section01;

import com.ohgiraffers.model.dto.Employee2DTO;
import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.section01.service.App4Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static com.ohgiraffers.common.JDBCTemplate.*;
public class Application04 {

    public static void main(String[] args){

        /*
        * 객체(Object)는 사전적인 정의로 실제로 존재하는 것을 말한다.
        * 붕어빵틀 붕어빵틀 = new 붕어빵틀();(붕어빵 예시가 올바르다고 보기 힘든 이유)
        * 사람 김연아 = new 사람();
        *
        * 개체
        * 생물학에서의 개체(個體)는 하나의 생물체를 뜻한다.
        *
        * 사람 손연재 = new 사람(); <- 인스턴스
        * 사람 이준효 = new 사람(); <- 인스턴스
        * 결혼하다(사람 누구?);
        * 손연재.결혼하다(이준효);
        *
        * 사람.결혼하다(사람) -> 이게 아닌 이유
        * 손연재 + 이준효
        *
        * 아래의 정답 중 개체를 골라주세요
        * 1. 쥐
        * 2. 미키마우스
        * 3. 제리
        * 4. 사람
        * 5. 손연재
        *  -> 2, 3, 5
        * */

        /*
        8 200
        * 선동일
        * 621235-1985634
        * sun_di@greedy.com
        * 01099546325
        * D9
        * J1
        * S1
        * 8000000
        * 0.3
        * 1990-02-06
        * N
        * */
        // 기본 생성시
//        Employee2DTO nonBuilder = new Employee2DTO();
//        nonBuilder.setEmpId("200");
//        nonBuilder.setEmpName("선동일");
//        System.out.println(nonBuilder);
//
//        EmployeeDTO builderEmp = new EmployeeDTO() // builder 패턴의 하나
//                    .setEmpNo("621235-1985634")
//                    .setBonus(0.3)
//                    .setEmpId("200")
//                    .setJobCode("j1");
//        System.out.println(builderEmp);

        Connection con = getConnection();
        App4Service service = new App4Service();
        Statement stmt = null;
        ResultSet rset = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("조회할 사번을 입력해주세요 : ");
        String empId = scanner.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
        EmployeeDTO emp = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query); // 조회된 정보는 모두 rset에 들어있다.
            if(rset.next()) {
                emp = new EmployeeDTO()
                        .setEmpNo(rset.getString("EMP_NO"))
                        .setEmpId(rset.getString("EMP_ID"))
                        .setEmpName(rset.getString("EMP_NAME"))
                        .setEmail(rset.getString("EMAIL"))
                        .setPhone(rset.getString("PHONE"))
                        .setBonus(rset.getDouble("BONUS"));
            }
//            service.printEmployee(rset);
            service.print(emp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(stmt);
        }

    }
}

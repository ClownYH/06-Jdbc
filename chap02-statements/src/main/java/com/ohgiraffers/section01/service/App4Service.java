package com.ohgiraffers.section01.service;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class App4Service {

    /**
    * 사원의 정보를 출력하는 메소드
     * @param ResultSet rset
    * */
    public void printEmployee(ResultSet rset){

        try {
            System.out.println(rset.getString("emp_no"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
        }
    }

    public void print(EmployeeDTO emp){
        System.out.println(emp);
    }
}

package com.ohgiraffers.section01;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application05 {

    public static void main(String[] args){

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        EmployeeDTO row;

        String query = "SELECT * FROM EMPLOYEE";
        List<EmployeeDTO> empList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            while (rset.next()){
                row = new EmployeeDTO()
                        .setEmpId(rset.getString(1))
                        .setEmpName(rset.getString(2))
                        .setEmpNo(rset.getString(3))
                        .setPhone(rset.getString(5))
                        .setEmail(rset.getString(4));
                empList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }
        empListPrint(empList);
    }

    public static void empListPrint(List<EmployeeDTO> list){
        for(EmployeeDTO emp : list){
            System.out.println(emp);
        }
    }
}

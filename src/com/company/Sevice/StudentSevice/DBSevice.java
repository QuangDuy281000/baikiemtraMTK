package com.company.Sevice.StudentSevice;

import com.company.DB.DBconnection;
import com.company.Entity.Student;
import com.company.Sevice.IStudentSevice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DBSevice
        implements IStudentSevice {
    public List<Student> docthongtin(){
        Connection conn = DBconnection.Creatconnection();
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Student";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setMasv(rs.getString("masv"));
                student.setHoten(rs.getString("hoten"));
                student.setGioitinh(rs.getString("gioitinh"));
                student.setNgaysinh(rs.getString("ngaysinh"));
                list.add(student);
            }
            ptmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> hienthigiamdanngaysinh() {
        DBSevice dbSevice = new DBSevice();
        List<Student> list = dbSevice.docthongtin();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Collections.sort(list,(o1, o2)->{
            try {
                Date date1= format.parse(o1.getNgaysinh());
                Date date2= format.parse(o1.getNgaysinh());
                return  date1.compareTo(date2) >=0?-1:1;
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        });
        return list;
    }

    @Override
    public List<Student> search(String name) {
        Connection conn = DBconnection.Creatconnection();
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Student WHERE hoten LIKE N'%"+name+"%'";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setMasv(rs.getString("masv"));
                student.setHoten(rs.getString("hoten"));
                student.setGioitinh(rs.getString("gioitinh"));
                student.setNgaysinh(rs.getString("ngaysinh"));
                list.add(student);
            }
            ptmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(String namsinh) {
        Connection conn = DBconnection.Creatconnection();
        String sql = "DELETE FROM dbo.Student WHERE YEAR(CONVERT(DATETIME,ngaysinh,104)) < ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,namsinh);
            ptmt.executeUpdate();
            ptmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

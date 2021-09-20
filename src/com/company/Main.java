package com.company;

import com.company.Entity.Student;
import com.company.Sevice.IStudentSevice;
import com.company.Sevice.StudentSevice.SeviceFactori;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        IStudentSevice svc = SeviceFactori.getSevice();
        System.out.println("Doc danh sach cac sinh vien");
        List<Student> list0 = svc.docthongtin();
        for(Student o:list0){
            System.out.println(o.getMasv()+" - "+o.getHoten()+" - "+o.getGioitinh()+" - "+o.getNgaysinh());
        }



        System.out.println("Hien thi danh sach theo thu tu giam dan cua ngay sinh");
        List<Student> list = svc.hienthigiamdanngaysinh();
        for(Student o:list){
            System.out.println(o.getMasv()+" - "+o.getHoten()+" - "+o.getGioitinh()+" - "+o.getNgaysinh());
        }

        System.out.println("tim theo ten");
        List<Student> list1 = svc.search("Nguyễn");
        for(Student o:list1){
            System.out.println(o.getMasv()+" - "+o.getHoten()+" - "+o.getGioitinh()+" - "+o.getNgaysinh());
        }

        svc.delete("1990");
        System.out.println("Danh Sach sau xoa");
        List<Student> list2 = svc.docthongtin();
        for(Student o:list2){
            System.out.println(o.getMasv()+" - "+o.getHoten()+" - "+o.getGioitinh()+" - "+o.getNgaysinh());
        }
    }
}

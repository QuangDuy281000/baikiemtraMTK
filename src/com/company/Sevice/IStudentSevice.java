package com.company.Sevice;

import com.company.Entity.Student;

import java.util.List;

public interface IStudentSevice {
   List<Student> docthongtin();

   List<Student> hienthigiamdanngaysinh();
   List<Student> search(String name);
   void delete(String namsinh);
}

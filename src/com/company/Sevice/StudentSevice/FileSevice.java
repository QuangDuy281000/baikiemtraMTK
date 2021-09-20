package com.company.Sevice.StudentSevice;

import com.company.Entity.Student;
import com.company.Sevice.IStudentSevice;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FileSevice implements IStudentSevice {
    @Override
    public List<Student> docthongtin() {
        List<Student> studentList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("D:\\student.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                Student student = new Student();
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                student.setMasv(txt[0]);
                student.setHoten(txt[1]);
                student.setGioitinh(txt[2]);
                student.setNgaysinh(txt[3]);
                studentList.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> hienthigiamdanngaysinh() {
        FileSevice fileSevice = new FileSevice();
        List<Student> list = fileSevice.docthongtin();
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
        List<Student> studentList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("D:\\student.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                Student student = new Student();
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                if (txt[1].contains(name)){
                    student.setMasv(txt[0]);
                    student.setHoten(txt[1]);
                    student.setGioitinh(txt[2]);
                    student.setNgaysinh(txt[3]);
                    studentList.add(student);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void delete(String namsinh) {
        FileSevice fileSevice = new FileSevice();
        List<Student> list = fileSevice.docthongtin();
        for (Student o:list){
            String str= o.getNgaysinh().substring(6);
            if(str.compareTo(namsinh)<0){
                list.remove(o);
            }
            else {
                try {
                    FileWriter fw = new FileWriter("D:\\outputstudent.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(o.toString());
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

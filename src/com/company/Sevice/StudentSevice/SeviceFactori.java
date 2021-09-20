package com.company.Sevice.StudentSevice;

import com.company.Sevice.IStudentSevice;

public class SeviceFactori {

    public static IStudentSevice getSevice(){
//        IStudentSevice sevice = new DBSevice();
        IStudentSevice sevice = new FileSevice();
        return sevice;
    }
}

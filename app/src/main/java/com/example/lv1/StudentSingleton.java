package com.example.lv1;

import java.util.ArrayList;

public class StudentSingleton {
    private static volatile StudentSingleton oInstance = null;
    private ArrayList<Student> lista_studenata = new ArrayList<Student>();
    public static StudentSingleton getInstance() {
        if(oInstance == null) {
            synchronized (StudentSingleton.class) {
                if (oInstance == null)
                    oInstance = new StudentSingleton();
            }
        }
        return oInstance;
    }
    public ArrayList<Student> getStudents() {
        return lista_studenata;
    }
    public void addStudent(Student student) {
        lista_studenata.add(student);
    }
}

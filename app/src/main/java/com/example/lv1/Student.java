package com.example.lv1;

public class Student {
    String ime;
    String prezime;
    String predmet;

    public Student(String ime, String prezime, String predmet) {
        this.ime = ime;
        this.prezime = prezime;
        this.predmet = predmet;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getPredmet() {
        return predmet;
    }
}
package com.carlesramos.practicagestionmaildef.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String birth;
    private int foto;
    private String email;
    private String phone1;
    private String phone2;
    private String adress;

    public Contact(int id, String name, String firstSurname, String secondSurname, String birth, int foto, String email, String phone1, String phone2, String adress) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.birth = birth;
        this.foto = foto;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getBirth() {
        return birth;
    }

    public int getFoto() {
        return foto;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getAdress() {
        return adress;
    }
}

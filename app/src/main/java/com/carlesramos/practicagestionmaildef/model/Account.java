package com.carlesramos.practicagestionmaildef.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private int id;
    private String name;
    private String firstSurname;
    private String email;
    private ArrayList<Mail> mails;
    private ArrayList<Contact> conacts;

    public Account(int id, String name, String firstSurname, String email
            , ArrayList<Mail> mails, ArrayList<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
        this.mails = mails;
        this.conacts = contacts;
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

    public String getEmail() {
        return email;
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    public ArrayList<Contact> getConacts() {
        return conacts;
    }
}

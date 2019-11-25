package com.carlesramos.practicagestionmaildef.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name;
    private String surename;
    private String mail;

    public Account(int id, String name, String surename, String mail) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public String getMail() {
        return mail;
    }
}

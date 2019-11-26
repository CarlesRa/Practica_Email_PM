package com.carlesramos.practicagestionmaildef.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Account implements Serializable {

    private int id;
    private String name;
    private String firstSurname;
    private String email;
    private ArrayList<Mail> mails;
    private ArrayList<Contact> conacts;
    private ArrayList<Mail> mailsRecibidos;
    private ArrayList<Mail> mailsEnviados;
    private ArrayList<Mail> mailsSpam;

    public Account(int id, String name, String firstSurname, String email
            , ArrayList<Mail> mails, ArrayList<Contact> contacts) {

        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
        this.mails = mails;
        this.conacts = contacts;
        mailsRecibidos = new ArrayList<>();
        mailsEnviados = new ArrayList<>();
        mailsSpam = new ArrayList<>();
        llenarRecibidos();
        llenarEnviados();
        llenarEspam();

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

    public ArrayList<Mail> getMailsRecibidos() {
        Collections.sort(mailsRecibidos);
        return mailsRecibidos;
    }

    public ArrayList<Mail> getMailsEnviados() {
        Collections.sort(mailsEnviados);
        return mailsEnviados;
    }

    public ArrayList<Mail> getMailsSpam() {
        Collections.sort(mailsSpam);
        return mailsSpam;
    }


    public void llenarRecibidos(){
        for (int i=0; i<mails.size(); i++){
            if (mails.get(i).getTo().equals(this.getEmail())){
                mailsRecibidos.add(mails.get(i));
            }
        }
    }

    public void llenarEnviados(){
        for (int i=0; i<mails.size(); i++){
            if (!mails.get(i).getTo().equals(this.getEmail())){
                mailsEnviados.add(mails.get(i));
            }
        }
    }

    public void llenarEspam(){
        for (int i=0; i<mails.size(); i++){
            if (mails.get(i).isSpam()){
                mailsSpam.add(mails.get(i));
            }
        }
    }
}

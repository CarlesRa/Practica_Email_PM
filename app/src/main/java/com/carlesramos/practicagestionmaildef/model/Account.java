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
    private ArrayList<Mail> mailsNoLeidos;
    private ArrayList<Mail> mailsEnviados;
    private ArrayList<Mail> mailsSpam;
    private ArrayList<Mail> mailsBorrados;


    public Account(int id, String name, String firstSurname, String email
            , ArrayList<Mail> mails, ArrayList<Contact> contacts) {

        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
        this.mails = mails;
        this.conacts = contacts;

        /*mailsNoLeidos = new ArrayList<>();
        mailsEnviados = new ArrayList<>();
        mailsSpam = new ArrayList<>();
        mailsBorrados = new ArrayList<>();
        llenarNoLeidos();
        llenarEnviados();
        llenarEspam();
        llenarBorrados();*/

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
        Collections.sort(mails);
        return mails;
    }

    public ArrayList<Contact> getConacts() {
        return conacts;
    }

    /*public ArrayList<Mail> getMailsNoLeidos() {
        Collections.sort(mailsNoLeidos);
        return mailsNoLeidos;
    }

    public ArrayList<Mail> getMailsEnviados() {
        Collections.sort(mailsEnviados);
        return mailsEnviados;
    }

    public ArrayList<Mail> getMailsSpam() {
        Collections.sort(mailsSpam);
        return mailsSpam;
    }

    public ArrayList<Mail> getMailsBorrados() {
        return mailsBorrados;
    }

    public void llenarNoLeidos(){
        for (int i=0; i<mails.size(); i++){
            for (int z=0; z<conacts.size(); z++) {
                if (!mails.get(i).isReaded() && mails.get(i).getTo().equals(conacts.get(z).getEmail())
                && !mails.get(i).isDeleted()) {
                    mailsNoLeidos.add(mails.get(i));
                }
            }
        }
    }

    public void llenarEnviados(){
        for (int i=0; i<mails.size(); i++){
            if (!mails.get(i).getTo().equals(this.getEmail()) && !mails.get(i).isDeleted()){
                mailsEnviados.add(mails.get(i));
            }
        }
    }

    public void llenarEspam(){
        for (int i=0; i<mails.size(); i++){
            if (mails.get(i).isSpam() && !mails.get(i).isDeleted()){
                mailsSpam.add(mails.get(i));
            }
        }
    }

    public void llenarBorrados(){
        for (int i=0; i<mails.size(); i++){
            if (mails.get(i).isDeleted() && !mails.get(i).getTo().equals(this.getEmail())){
                mailsBorrados.add(mails.get(i));
            }
        }
    }*/
}

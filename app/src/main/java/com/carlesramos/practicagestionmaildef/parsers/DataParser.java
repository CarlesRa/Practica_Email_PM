package com.carlesramos.practicagestionmaildef.parsers;

import android.content.Context;
import com.carlesramos.practicagestionmaildef.model.Account;
import com.carlesramos.practicagestionmaildef.model.Contact;
import com.carlesramos.practicagestionmaildef.model.Mail;
import com.germangascon.practicagestionmaildef.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataParser {
    private ArrayList<Contact> contacts;
    private ArrayList<Mail> mails;
    private Account account;
    private InputStream dataFile;
    private ArrayList<Mail> mailsNoLeidos;
    private ArrayList<Mail> mailsEnviados;
    private ArrayList<Mail> mailsSpam;
    private ArrayList<Mail> mailsBorrados;
    public DataParser(Context c) {

        dataFile = c.getResources().openRawResource(R.raw.correos);
    }

    public boolean parse(){

        boolean parsed = false;
        String json = null;
        contacts = null;
        mails = new ArrayList<>();
        account = null;
        mailsNoLeidos = new ArrayList<>();
        mailsEnviados = new ArrayList<>();
        mailsSpam = new ArrayList<>();
        mailsBorrados = new ArrayList<>();

        try {
            int size = dataFile.available();
            byte[] buffer = new byte[size];
            dataFile.read(buffer);
            dataFile.close();
            json = new String(buffer,"UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray  jsonArray= new JSONArray(tokener);
            contacts = new ArrayList<>();
            mails = new ArrayList<>();

            //Plene el conte de correu
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONObject jsonObjectAccount = jsonObject.getJSONObject("myAccount");
            int id = jsonObjectAccount.getInt("id");
            String name = jsonObjectAccount.getString("name");
            String firstSurename = jsonObjectAccount.getString("firstSurname");
            String email = jsonObjectAccount.getString("email");

            //Plene el array de contactes
            JSONArray jsonArrayContacts = jsonObject.getJSONArray("contacts");
            for (int i=1; i<jsonArrayContacts.length(); i++){
                JSONObject contactsJson = jsonArrayContacts.getJSONObject(i);
                int idContact = contactsJson.getInt("id");
                String nameContact = contactsJson.getString("name");
                String firstSurnameContact = contactsJson.getString("firstSurname");
                String secondSurnameContact = contactsJson.getString("secondSurname");
                String birthContact = contactsJson.getString("birth");
                int fotoContact = contactsJson.getInt("foto");
                String emailContact = contactsJson.getString("email");
                String phone1Contact = contactsJson.getString("phone1");
                String phone2Contact = contactsJson.getString("phone2");
                String adressContact = contactsJson.getString("address");
                contacts.add(new Contact(idContact, nameContact, firstSurnameContact, secondSurnameContact
                , birthContact, fotoContact, emailContact, phone1Contact, phone2Contact, adressContact));
            }

            JSONArray arrayMails = jsonObject.getJSONArray("mails");
            for (int i=0; i<arrayMails.length(); i++){
                JSONObject mailsJson = arrayMails.getJSONObject(i);
                String from = mailsJson.getString("from");
                String to = mailsJson.getString("to");
                String subject = mailsJson.getString("subject");
                String body = mailsJson.getString("body");
                String sentOn = mailsJson.getString("sentOn");
                boolean readed = mailsJson.getBoolean("readed");
                boolean deleted = mailsJson.getBoolean("deleted");
                boolean spam = mailsJson.getBoolean("spam");
                mails.add(new Mail(from, to, subject, body, sentOn, readed, deleted, spam));
            }
            account = new Account(id, name, firstSurename, email, mails, contacts);

            parsed = true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        llenarNoLeidos();
        llenarEnviados();
        llenarEspam();
        llenarBorrados();

        return parsed;
    }

    public Account getAccount(){
        return account;
    }

    public ArrayList<Contact> getContacts(){
        return contacts;
    }

    public ArrayList<Mail> getMails(){
        return mails;
    }

    public ArrayList<Mail> getMailsBorrados() {
        return mailsBorrados;
    }

    public ArrayList<Mail> getMailsEnviados() {
        return mailsEnviados;
    }

    public ArrayList<Mail> getMailsNoLeidos() {
        return mailsNoLeidos;
    }

    public ArrayList<Mail> getMailsSpam() {
        return mailsSpam;
    }

    public void llenarNoLeidos(){
        for (int i=0; i<mails.size(); i++){
            for (int z=0; z<contacts.size(); z++) {
                if (!mails.get(i).isReaded() && mails.get(i).getTo().equals(contacts.get(z).getEmail())
                        && !mails.get(i).isDeleted()) {
                    mailsNoLeidos.add(mails.get(i));
                }
            }
        }
    }

    public void llenarEnviados(){
        for (int i=0; i<mails.size(); i++){
            if (!mails.get(i).getTo().equals(account.getEmail()) && !mails.get(i).isDeleted()){
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
            if (mails.get(i).isDeleted() && !mails.get(i).getTo().equals(account.getEmail())){
                mailsBorrados.add(mails.get(i));
            }
        }
    }
}

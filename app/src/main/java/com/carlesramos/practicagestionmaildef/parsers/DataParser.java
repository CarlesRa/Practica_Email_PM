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

    public DataParser(Context c) {

        dataFile = c.getResources().openRawResource(R.raw.correos);
    }

    public boolean parse(){

        boolean parsed = false;
        String json = null;
        contacts = null;
        mails = null;
        account = null;

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
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String firstSurename = jsonObject.getString("firstSurename");
            String email = jsonObject.getString("email");

            //Plene el array de contactes
            JSONArray jsonArrayContacts = jsonObject.getJSONArray("contacts");
            for (int i=1; i<jsonArrayContacts.length(); i++){
                JSONObject contactsJson = jsonArrayContacts.getJSONObject(i);
                int idContact = contactsJson.getInt("id");
                String nameContact = contactsJson.getString("name");
                String firstSurnameContact = contactsJson.getString("firstSurname");
                String secondSurnameContact = contactsJson.getString("secondSurname");
                String birthContact = contactsJson.getString("birth");
                int fotoContact = contactsJson.getInt("foro");
                String emailContact = contactsJson.getString("email");
                String phone1Contact = contactsJson.getString("phone1");
                String phone2Contact = contactsJson.getString("phone2");
                String adressContact = contactsJson.getString("adress");
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


}

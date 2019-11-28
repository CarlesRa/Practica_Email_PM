package com.carlesramos.practicagestionmaildef.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Mail implements Serializable, Comparable<Mail> {

    private String from;
    private String to;
    private String subject;
    private String body;
    private String sentOn;
    private Calendar fechaEnvio;
    private boolean readed;
    private boolean deleted;
    private boolean spam;

    public Mail(String from, String to, String subject, String body, String sentOn, boolean readed, boolean deleted, boolean spam) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.sentOn = sentOn;
        this.fechaEnvio = Calendar.getInstance();
        fechaEnvio.setTime(convertToCalendar(sentOn));
        this.readed = readed;
        this.deleted = deleted;
        this.spam = spam;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getSentOn() {
        return sentOn;
    }

    public boolean isReaded() {
        return readed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isSpam() {
        return spam;
    }

    public Calendar getFechaEnvio() {
        return fechaEnvio;
    }

    public Date convertToCalendar(String fecha){
        Date c = null;
        try {
            c = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    @Override
    public int compareTo(Mail mail) {
        if (this.getFechaEnvio().before(mail.getFechaEnvio())){
            return 1;
        }
        if (this.getFechaEnvio().after((mail.getFechaEnvio()))){
            return -1;
        }
        return 0;
    }
}

package com.carlesramos.practicagestionmaildef.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.carlesramos.practicagestionmaildef.interficies.IMailListener;
import com.carlesramos.practicagestionmaildef.model.Account;
import com.carlesramos.practicagestionmaildef.model.Contact;
import com.carlesramos.practicagestionmaildef.model.Mail;
import com.germangascon.practicagestionmaildef.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.RecibidosViewHolder>{

    private Account acount;
    private Context context;
    private IMailListener listener;
    private MenuItem item;

    public MailAdapter(Context c, Account a, IMailListener listener, MenuItem item){

        this.acount = a;
        this.context = c;
        this.listener = listener;
        this.item = item;
    }

    @NonNull
    @Override
    public RecibidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_listado, parent, false);
        RecibidosViewHolder viewHolder = new RecibidosViewHolder(itemView, context, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecibidosViewHolder holder, int position) {
        ArrayList<Contact> contacts = acount.getConacts();
        int id = item.getItemId();
        Mail mail = null;
        Contact contact = null;
        ArrayList<Mail> mails;
        if (id == R.id.nav_recibidos) {
            mails = acount.getMails();
            mail = mails.get(position);
            for (int z=0; z<contacts.size(); z++) {
                if (mail.getFrom().equals(contacts.get(z).getEmail())) {
                    contact = contacts.get(z);
                }
            }
        }
        else if (id == R.id.nav_enviados) {
            mails = acount.getMailsEnviados();
            mail = mails.get(position);
            for (int z=0; z<contacts.size(); z++) {
                if (mail.getTo().equals(contacts.get(z).getEmail())) {
                    contact = contacts.get(z);
                }
            }
        }
        else if (id == R.id.nav_noleidos){
            mails = acount.getMailsNoLeidos();
            mail = mails.get(position);
            for (int z=0; z<contacts.size(); z++) {
                if (mail.getTo().equals(contacts.get(z).getEmail())) {
                    contact = contacts.get(z);
                }
            }
        }
        else if (id == R.id.nav_borrados){
            mails = acount.getMailsBorrados();
            mail = mails.get(position);
            for (int z=0; z<contacts.size(); z++) {
                if (mail.getTo().equals(contacts.get(z).getEmail())) {
                    contact = contacts.get(z);
                }
            }
        }
        else if (id == R.id.nav_spam) {
            mails = acount.getMailsSpam();
            mail = mails.get(position);
        }

        if (contact != null){
            holder.bindMail(mail, contact, item);
        }
        else if (contact == null && id == R.id.nav_spam){
            holder.bindMail(mail, contact, item);
        }

    }

    //TODO pensar en la forma de camviar la forma de traure nombre de items
    @Override
    public int getItemCount() {

        if (item != null){
            int id = item.getItemId();
            if (id == R.id.nav_recibidos) {
                return acount.getMails().size();
            }
            else if (id == R.id.nav_enviados) {
                return acount.getMailsEnviados().size();
            }
            else if (id == R.id.nav_spam) {
                return acount.getMailsSpam().size();
            }
            else if (id == R.id.nav_noleidos){
                return acount.getMailsNoLeidos().size();
            }
            else if (id == R.id.nav_borrados){
                return acount.getMailsBorrados().size();
            }
        }
        return 0;
        //return mails.size();
    }

    public static class RecibidosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivFoto;
        private TextView tvNombre;
        private TextView tvAsunto;
        private TextView tvMensaje;
        private TextView tvDiaMes;
        private TextView tvHora;
        private Context context;
        private IMailListener listener;
        private Mail m;

        public RecibidosViewHolder(@NonNull View itemView, Context context, IMailListener listener) {
            super(itemView);
            this.context = context;

            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAsunto = itemView.findViewById(R.id.tvAsunto);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvDiaMes = itemView.findViewById(R.id.tvDiaMes);
            tvHora = itemView.findViewById(R.id.tvHora);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindMail(Mail m, Contact c, MenuItem item){

            this.m = m;
            int id = item.getItemId();

            if (c != null){
                String nameFoto = "c" + c.getFoto();
                int resID = context.getResources().getIdentifier(nameFoto, "drawable", context.getPackageName());
                ivFoto.setImageResource(resID);
            }
            else{
                int resIDDefault = context.getResources().getIdentifier("default_person","drawable",context.getPackageName());
                ivFoto.setImageResource(resIDDefault);
            }
            if (id == R.id.nav_spam) {
                tvNombre.setText(m.getFrom());
            }
            else {
                tvNombre.setText(c.getName() + " " + c.getFirstSurname() + " " + c.getSecondSurname());
            }
            tvAsunto.setText(m.getSubject());
            tvMensaje.setText(m.getBody());
            String[] fechaComleta = m.getSentOn().split("-");
            String[] diaHora = fechaComleta[2].split(" ");
            String dia = diaHora[0];
            String hora = diaHora[1];
            Locale l = new Locale("es_ES");
            String month = m.getFechaEnvio().getDisplayName(Calendar.MONTH, Calendar.LONG
                    , l.getDefault()).toLowerCase().substring(0,3) + ".";

            tvDiaMes.setText(dia + " " + month);
            tvHora.setText(hora);
            if (!m.isReaded()){
                tvNombre.setTypeface(null, Typeface.BOLD);
                tvAsunto.setTypeface(null, Typeface.BOLD);
                tvDiaMes.setTextColor(Color.BLUE);
                tvHora.setTextColor(Color.BLUE);
            }
            else{
                tvNombre.setTypeface(null, Typeface.NORMAL);
                tvAsunto.setTypeface(null, Typeface.NORMAL);
                tvDiaMes.setTextColor(Color.BLACK);
                tvHora.setTextColor(Color.BLACK);
            }
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onMailSelected(m);
            }
        }
    }
}

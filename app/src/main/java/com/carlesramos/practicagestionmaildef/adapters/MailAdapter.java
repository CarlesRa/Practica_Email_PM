package com.carlesramos.practicagestionmaildef.adapters;

import android.content.Context;
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

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.RecibidosViewHolder>{

    private Account acount;
    private Context context;
    private IMailListener listener;
   /* private ArrayList<Mail> mailsRecibidos;
    private ArrayList<Mail> mailsEnviados;
    private Arraylist*/
    private MenuItem item;

    public MailAdapter(Context c, Account a, IMailListener listener, MenuItem item){
        this.acount = a;
        this.context = c;
        this.listener = listener;
        this.item = item;
        //mailsRecibidos = new ArrayList<>();
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
        ArrayList<Mail> mails;
        mails = new ArrayList<>();
        int id = item.getItemId();
        Mail mail = null;
        Contact contact = null;
        if (id == R.id.nav_recibidos) {
            mails = acount.getMailsRecibidos();
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
        else if (id == R.id.nav_spam) {
            mails = acount.getMailsSpam();
        }

        /*Mail mail = mails.get(position);
        Contact contact = null;
        for (int z=0; z<contacts.size(); z++) {
            if (mail.getFrom().equals(contacts.get(z).getEmail())) {
                contact = contacts.get(z);
            }
        }*/
        if (contact != null){
            holder.bindMail(mail, contact, item);
        }

    }

    @Override
    public int getItemCount() {
        int id = item.getItemId();

        if (id == R.id.nav_recibidos) {
            return acount.getMailsRecibidos().size();
        }
        else if (id == R.id.nav_enviados) {
            return acount.getMailsEnviados().size();
        }
        else if (id == R.id.nav_spam) {
            return acount.getMailsSpam().size();
        }
        return 0;
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
            int id = item.getItemId();
            String nameFoto = "c" + c.getFoto();
            int resID = context.getResources().getIdentifier(nameFoto, "drawable", context.getPackageName());
            if (resID != 0){
                ivFoto.setImageResource(resID);
            }
            else{
                int resIDDefault = context.getResources().getIdentifier("defaultFoto","drawable",context.getPackageName());
                ivFoto.setImageResource(resIDDefault);
            }
            if (id == R.id.nav_recibidos) {
                tvNombre.setText(m.getFrom());
            }
            else if (id == R.id.nav_enviados) {
                tvNombre.setText(m.getTo());
            }
            else if (id == R.id.nav_spam) {
                tvNombre.setText(m.getTo());
            }
            //tvNombre.setText(m.getFrom());
            tvAsunto.setText(m.getSubject());
            tvMensaje.setText(m.getBody());
            String[] fechaComleta = m.getSentOn().split("-");
            String mes = fechaComleta[1];
            String[] diaHora = fechaComleta[2].split(" ");
            String dia = diaHora[0];
            String hora = diaHora[1];
            switch (Integer.parseInt(mes)){
                case 1:
                    mes = "ene.";
                    break;
                case 2:
                    mes = "feb.";
                    break;
                case 3:
                    mes = "mar.";
                    break;
                case 4:
                    mes = "abr.";
                    break;
                case 5:
                    mes = "may.";
                    break;
                case 6:
                    mes = "jun.";
                    break;
                case 7:
                    mes = "jul.";
                    break;
                case 8:
                    mes = "ago.";
                    break;
                case 9:
                    mes = "sep.";
                    break;
                case 10:
                    mes = "oct.";
                    break;
                case 11:
                    mes = "nov.";
                    break;
                case 12:
                    mes = "dic.";
                    break;
            }
            tvDiaMes.setText(dia + " " + mes);
            tvHora.setText(hora);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onMailSelected(getAdapterPosition());
            }
        }
    }


   /* public void llenarRecibidos(ArrayList<Mail> mails){
        for (int i=0; i<mails.size(); i++){
            if (mails.get(i).getTo().equals(acount.getEmail()) && mails.get(i).isDeleted() == false
            && mails.get(i).isSpam() == false){
                mailsRecibidos.add(mails.get(i));
            }
        }
    }*/

}

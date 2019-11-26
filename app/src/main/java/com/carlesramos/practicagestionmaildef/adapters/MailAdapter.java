package com.carlesramos.practicagestionmaildef.adapters;

import android.content.Context;
import android.view.LayoutInflater;
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

    public MailAdapter(Context c, Account a, IMailListener listener){
        this.acount = a;
        this.context = c;
        this.listener = listener;
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

        Mail mail = acount.getMails().get(position);
        ArrayList<Contact> contacts = acount.getConacts();
        Contact contact = null;
        for (int i=0; i<contacts.size(); i++){
            if (mail.getFrom().equals(contacts.get(i).getEmail())){
                contact = contacts.get(i);
                return;
            }
        }
        holder.bindMail(mail, contact);
    }

    @Override
    public int getItemCount() {
        return acount.getMails().size();
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
            tvDiaMes = itemView.findViewById(R.id.tvHora);
            tvHora = itemView.findViewById(R.id.tvHora);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindMail(Mail m, Contact c){
            //TODO averiguar que pasarle para poder averiguar la foto
            String nameFoto = "c" + c.getFoto();
            int resID = context.getResources().getIdentifier(nameFoto, "drawable", context.getPackageName());
            if (resID != 0){
                ivFoto.setImageResource(resID);
            }
            else{
                int resIDDefault = context.getResources().getIdentifier("defaultFoto","drawable",context.getPackageName());
                ivFoto.setImageResource(resIDDefault);
            }
            tvNombre.setText(c.getName());
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
}

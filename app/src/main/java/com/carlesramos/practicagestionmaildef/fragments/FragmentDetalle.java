package com.carlesramos.practicagestionmaildef.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carlesramos.practicagestionmaildef.model.Contact;
import com.carlesramos.practicagestionmaildef.model.Mail;
import com.carlesramos.practicagestionmaildef.parsers.DataParser;
import com.germangascon.practicagestionmaildef.R;

import java.util.ArrayList;

public class FragmentDetalle extends Fragment {
    private ImageView ivFoto;
    private TextView tvNombre;
    private TextView tvFechaHora;
    private TextView tvAsunto;
    private TextView tvMensaje;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frg_detalle_emails, container, false);
        ivFoto = layout.findViewById(R.id.ivDetalleFoto);
        tvNombre = layout.findViewById(R.id.tvDetalleNom);
        tvFechaHora = layout.findViewById(R.id.tvDetalleFechaHora);
        tvAsunto = layout.findViewById(R.id.tvDetalleAsunto);
        tvMensaje = layout.findViewById(R.id.tvDetalleMensaje);
        return layout;
    }

    public void mostrarDetalle(Mail m){
        this.context = getActivity();
        DataParser parser = new DataParser(getActivity());
        Contact c = null;
        if (parser.parse()){
            ArrayList<Contact>contacts = parser.getContacts();
            for (int i=0; i<contacts.size(); i++){
                if (contacts.get(i).getEmail().equals(m.getFrom())){
                    c = contacts.get(i);

                }
            }

            if (c != null){
                String nameFoto = "c" + c.getFoto();
                int resID = getResources().getIdentifier(nameFoto, "drawable", getActivity().getPackageName());
                ivFoto.setImageResource(resID);
                tvNombre.setText(c.getName() + " " + c.getFirstSurname() + " " + c.getSecondSurname());
                tvFechaHora.setText(m.getSentOn());
                tvAsunto.setText(m.getSubject());
                tvMensaje.setText(m.getBody());
            }
            else{
                int resIDDefault = context.getResources().getIdentifier("default_person","drawable",context.getPackageName());
                ivFoto.setImageResource(resIDDefault);
                tvNombre.setText(m.getFrom());
                tvFechaHora.setText(m.getSentOn());
                tvAsunto.setText(m.getSubject());
                tvMensaje.setText(m.getBody());
            }
        }



    }
}

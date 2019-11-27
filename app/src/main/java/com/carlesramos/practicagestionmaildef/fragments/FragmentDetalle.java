package com.carlesramos.practicagestionmaildef.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.germangascon.practicagestionmaildef.R;

public class FragmentDetalle extends Fragment {
    private ImageView ivFoto;
    private TextView tvNombre;
    private TextView tvFechaHora;
    private TextView tvAsunto;
    private TextView tvMensaje;
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

    public void mostrarDetalle(){

    }
}

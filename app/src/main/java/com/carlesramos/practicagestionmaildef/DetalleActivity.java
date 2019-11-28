package com.carlesramos.practicagestionmaildef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.carlesramos.practicagestionmaildef.fragments.FragmentDetalle;
import com.carlesramos.practicagestionmaildef.model.Mail;
import com.germangascon.practicagestionmaildef.R;

public class DetalleActivity extends AppCompatActivity {
    public static final String EXTRA_TEXTO = "com.carlesramos.practicamail.EXTRA_TEXTO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalle detalle = (FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.frgDetalle);
        detalle.mostrarDetalle((Mail)getIntent().getSerializableExtra(EXTRA_TEXTO));
    }
}

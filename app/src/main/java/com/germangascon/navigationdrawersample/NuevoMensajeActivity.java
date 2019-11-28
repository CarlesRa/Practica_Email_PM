package com.germangascon.navigationdrawersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlesramos.practicagestionmaildef.MainActivity;
import com.germangascon.practicagestionmaildef.R;
import com.google.android.material.textfield.TextInputEditText;

public class NuevoMensajeActivity extends AppCompatActivity {
    private Button btEnviar;
    private EditText etMail;
    private EditText etAsunto;
    private TextInputEditText tiMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_mensaje);

        etMail = findViewById(R.id.etMail);
        etAsunto = findViewById(R.id.etAsunto);
        tiMensaje = findViewById(R.id.tiMensaje);

        btEnviar = findViewById(R.id.btNuevoCorreoEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etMail.getText().toString().equals("") || etAsunto.getText().toString().equals("") || tiMensaje.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Per aci no em pilles German!!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mensaje enviado!!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}

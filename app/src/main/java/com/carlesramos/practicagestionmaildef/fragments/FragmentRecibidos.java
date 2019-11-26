package com.carlesramos.practicagestionmaildef.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlesramos.practicagestionmaildef.interficies.IMailListener;
import com.carlesramos.practicagestionmaildef.model.Account;
import com.carlesramos.practicagestionmaildef.model.Contact;
import com.carlesramos.practicagestionmaildef.model.Mail;
import com.germangascon.practicagestionmaildef.R;

public class FragmentRecibidos extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frg_recividos, container, false);

        return layout;
    }


}

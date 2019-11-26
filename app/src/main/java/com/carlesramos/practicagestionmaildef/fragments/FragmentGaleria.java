package com.carlesramos.practicagestionmaildef.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.germangascon.practicagestionmaildef.R;

public class FragmentGaleria extends Fragment {
    private TextView tvSample;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.frg_recividos, container, false);
        //tvSample = layout.findViewById(R.id.tvSample);
        tvSample.setText("Galería");
        return layout;
    }
}
package com.germangascon.navigationdrawersample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentGaleria extends Fragment {
    private TextView tvSample;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_sample, container, false);
        tvSample = (TextView)layout.findViewById(R.id.tvSample);
        tvSample.setText("Galería");
        return layout;
    }
}

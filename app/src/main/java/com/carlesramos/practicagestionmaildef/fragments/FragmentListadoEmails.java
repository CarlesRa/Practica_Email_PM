package com.carlesramos.practicagestionmaildef.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.carlesramos.practicagestionmaildef.adapters.MailAdapter;
import com.carlesramos.practicagestionmaildef.interficies.IMailListener;
import com.carlesramos.practicagestionmaildef.model.Account;
import com.carlesramos.practicagestionmaildef.parsers.DataParser;
import com.germangascon.practicagestionmaildef.R;

public class FragmentListadoEmails extends Fragment {

    private RecyclerView rvRecibidos;
    private Account account;
    private IMailListener listener;
    private MenuItem item;

    public FragmentListadoEmails(MenuItem item){
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataParser parser = new DataParser(getActivity());
        if (parser.parse()){
            this.account = parser.getAccount();
        }
        return inflater.inflate(R.layout.frg_emails, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvRecibidos = getView().findViewById(R.id.rvEmails);
        rvRecibidos.setAdapter(new MailAdapter(getActivity(),account,listener, item));
        rvRecibidos.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void setMailListener(IMailListener listener){
        this.listener = listener;
    }

    public Account getAccount(){
        return account;
    }
}
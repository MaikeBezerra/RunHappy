package com.example.runhappy.ui.historico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.runhappy.R;

public class HistoricoFragment extends Fragment {

    private HistoricoViewModel historicoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historicoViewModel =
                ViewModelProviders.of(this).get(HistoricoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historico, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        historicoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
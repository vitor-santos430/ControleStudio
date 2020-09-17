package com.example.controlestudio.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.controlestudio.R;
import com.example.controlestudio.bancodedados.BancoDeDados;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        BancoDeDados bancoDeDados = new BancoDeDados(getContext());

        float totalGasto = bancoDeDados.obterSumGastos();
        final TextView labelGastos = root.findViewById(R.id.labelGastos);
        labelGastos.setText(String.valueOf(new DecimalFormat("R$#0.00").format(totalGasto)).replace('.',','));

        float totalReceita = bancoDeDados.obterSumReceitas();
        final TextView labelReceitas = root.findViewById(R.id.labelReceitas);
        labelReceitas.setText(String.valueOf(new DecimalFormat("R$#0.00").format(totalReceita)).replace('.',','));

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

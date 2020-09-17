package com.example.controlestudio.ui.overview;

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

public class OverviewFrament extends Fragment {

    private OverviewViewModel overviewViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        overviewViewModel = ViewModelProviders.of(this).get(OverviewViewModel.class);

        View root = inflater.inflate(R.layout.fragment_overview,container,false);

        final TextView textView = root.findViewById(R.id.text_geral);

        BancoDeDados bancoDeDados = new BancoDeDados(getContext());

        float totalGasto = bancoDeDados.obterSumGastos();
        float totalReceita = bancoDeDados.obterSumReceitas();

        float emMaos = totalReceita - totalGasto;
        String situacao;

        if(emMaos > 0){
            situacao = "Lucro Positivo";
        }else if(emMaos == 0){
            situacao = "Lucro Nulo";
        }else{
            situacao = "Lucro Negativo";
        }

        final TextView labelEmMaos = root.findViewById(R.id.labelEmMaos);
        labelEmMaos.setText(String.valueOf(new DecimalFormat("R$#0.00").format(emMaos)).replace('.',','));

        final TextView labelSituacao = root.findViewById(R.id.labelSituacao);
        labelSituacao.setText(situacao);

        overviewViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }
}

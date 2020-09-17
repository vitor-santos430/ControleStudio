package com.example.controlestudio.ui.gastos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.controlestudio.EditarGasto;
import com.example.controlestudio.EditarReceita;
import com.example.controlestudio.R;
import com.example.controlestudio.bancodedados.BancoDeDados;

public class GastosFragment extends Fragment {

    private GastosViewModel gastosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gastosViewModel =
                ViewModelProviders.of(this).get(GastosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gastos, container, false);
        final TextView textView = root.findViewById(R.id.text_title);

        BancoDeDados bancoDeDados = new BancoDeDados(getContext());
        final Cursor cursor = bancoDeDados.obterGastos();

        String[] nomeCampos = new String[]{"titulo","valor","data_gasto"};
        int[] idViews = new int[]{R.id.labelTitulo, R.id.labelValor, R.id.labelData};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getContext(),
                R.layout.modelo_lista_gasto,cursor,nomeCampos,idViews,0);

        ListView lista = (ListView) root.findViewById(R.id.Lista);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                cursor.moveToPosition(position);
                Intent intent = new Intent(getActivity(),  EditarGasto.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                Activity activity = new Activity();
                activity.finish();
            }
        });


        gastosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

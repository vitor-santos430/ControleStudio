package com.example.controlestudio.ui.gastos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GastosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GastosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui Estão Suas Despesas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
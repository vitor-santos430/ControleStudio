package com.example.controlestudio.ui.receitas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceitasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReceitasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui estão os Valores Recebidos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
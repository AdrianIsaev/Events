package com.example.betatest.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IzmeniViewModel extends ViewModel {
    private MutableLiveData<String> textLiveData = new MutableLiveData<>();

    public LiveData<String> getTextLiveData() {
        return textLiveData;
    }

    public void updateText(String text) {
        textLiveData.setValue(text);
    }
}
package com.example.betatest.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Vhod1ViewModel extends ViewModel {
    private MutableLiveData<Boolean> rememberMeLiveData = new MutableLiveData<>(false);
    private MutableLiveData<String> emailLiveData = new MutableLiveData<>("");
    private MutableLiveData<String> passwordLiveData = new MutableLiveData<>("");

    public LiveData<Boolean> getRememberMeLiveData() {
        return rememberMeLiveData;
    }

    public LiveData<String> getEmailLiveData() {
        return emailLiveData;
    }

    public LiveData<String> getPasswordLiveData() {
        return passwordLiveData;
    }

    public void setRememberMe(boolean rememberMe) {
        rememberMeLiveData.setValue(rememberMe);
    }

    public void setEmail(String email) {
        emailLiveData.setValue(email);
    }

    public void setPassword(String password) {
        passwordLiveData.setValue(password);
    }
}
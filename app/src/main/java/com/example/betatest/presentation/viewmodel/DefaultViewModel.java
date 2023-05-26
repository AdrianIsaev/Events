package com.example.betatest.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class DefaultViewModel extends ViewModel {    private FirebaseAuth mAuth;
    public DefaultViewModel() {
        mAuth = FirebaseAuth.getInstance();    }
    public void logoutUser() {
        mAuth.signOut();    }
}
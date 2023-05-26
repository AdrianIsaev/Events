package com.example.betatest.presentation.viewmodel;


import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrViewModel extends ViewModel {
    private FirebaseAuth mAuth;
    public RegistrViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }
    public void registerUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }
}

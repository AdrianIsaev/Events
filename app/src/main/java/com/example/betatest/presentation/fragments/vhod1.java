package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betatest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class vhod1 extends Fragment {
    private EditText email_login;
    private EditText password_login;
    private TextView button2;
    private SharedPreferences prefs;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vhod1, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email_login = view.findViewById(R.id.textView3);
        password_login = view.findViewById(R.id.textView6);
        button2 = view.findViewById(R.id.button2);
        TextView button3 = view.findViewById(R.id.button);


        TextView button4 = view.findViewById(R.id.button3);
        prefs = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String password = prefs.getString("password", null);
        boolean rememberMe = prefs.getBoolean("rememberMe", false);
        mAuth = FirebaseAuth.getInstance();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rememberMe && email !=null && password != null){
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Navigation.findNavController(v).navigate(R.id.action_vhod1_to_defaultfragment);
                                    }
                                    else{
                                        Toast.makeText(getContext(), "Ошибка автоматической аутентификации", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    if (email_login.getText().toString().isEmpty() || password_login.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Введите корректные данные", Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.signInWithEmailAndPassword(email_login.getText().toString(), password_login.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            SharedPreferences.Editor editor = prefs.edit();
                                            editor.putString("email", email_login.getText().toString());
                                            editor.putString("password", password_login.getText().toString());
                                            editor.putBoolean("rememberMe", true);
                                            editor.apply();

                                            Navigation.findNavController(v).navigate(R.id.action_vhod1_to_defaultfragment);
                                        } else {
                                            Toast.makeText(getContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vhod1_to_vosstanovka23);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vhod1_to_registr22);
            }
        });
    }
}
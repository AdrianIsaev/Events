package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betatest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class izmeni extends Fragment {
    public static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 43;
    EditText hhh;
    EditText password_register;
    AppCompatButton appCompatButton;

    public izmeni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.izmeni, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String text2 = sharedPreferences.getString("text", "");
        TextView name = view.findViewById(R.id.textView4);
        name.setText(text2);
        appCompatButton = view.findViewById(R.id.textViewRegistr7);


        hhh = view.findViewById(R.id.textViewRegistrfix2);


        password_register = view.findViewById(R.id.textViewRegistr6);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hhh.getText().toString()!=null || hhh.getText().toString()!="") {
                    String text = hhh.getText().toString();

                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    sharedPreferences.edit().putString("text", text).apply();
                    name.setText(text);
                    hhh.setText("");
                }
            }
        });
    }
}

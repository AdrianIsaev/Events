package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.betatest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class registr extends Fragment {
    private EditText email_register;
    private EditText password_register;
    private AppCompatButton button2;
    private ImageView imageView;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registr, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        button2 = view.findViewById(R.id.textViewRegistr7);
        email_register = view.findViewById(R.id.textViewRegistr4);
        password_register = view.findViewById(R.id.textViewRegistr6);
        imageView = view.findViewById(R.id.joniks);
        EditText editTex2t = view.findViewById(R.id.textViewRegistrfix2);
        mAuth = FirebaseAuth.getInstance();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registr2_to_vhod1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Введите корректные данные!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        navController.navigate(R.id.action_registr2_to_defaultfragment);
                                    }
                                    else{
                                        Toast.makeText(getContext(), "Введены некорректные данные!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                String text = editTex2t.getText().toString();
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                sharedPreferences.edit().putString("text", text).apply();
                    //navController.navigate(R.id.action_registr2_to_defaultfragment);
            }
        });
    }
}
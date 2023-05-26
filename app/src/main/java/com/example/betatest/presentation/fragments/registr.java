package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.betatest.R;
import com.example.betatest.presentation.viewmodel.RegistrViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class registr extends Fragment {
    private EditText emailEditText;
    private EditText passwordEditText;
    private AppCompatButton registerButton;
    private ImageView backButton;
    private EditText editText2;

    private NavController navController;
    private RegistrViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(this).get(RegistrViewModel.class);

        emailEditText = view.findViewById(R.id.textViewRegistr4);
        passwordEditText = view.findViewById(R.id.textViewRegistr6);
        registerButton = view.findViewById(R.id.textViewRegistr7);
        backButton = view.findViewById(R.id.joniks);
        editText2 = view.findViewById(R.id.textViewRegistrfix2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registr2_to_vhod1);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Введите корректные данные!", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.registerUser(email, password, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                navController.navigate(R.id.action_registr2_to_defaultfragment);
                            } else {
                                Toast.makeText(getContext(), "Введены некорректные данные!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                String text = editText2.getText().toString();
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                sharedPreferences.edit().putString("text", text).apply();
            }
        });
    }
}
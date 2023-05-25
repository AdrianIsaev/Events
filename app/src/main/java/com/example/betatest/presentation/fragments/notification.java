package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.betatest.R;
import com.example.betatest.data.storage.room.entity.ProjectModel;

import java.util.Calendar;


public class notification extends Fragment {
    private SharedPreferences sharedPreferences;
    private AppCompatButton buttonEvents;
    private AppCompatButton buttonCalend;
    private ProjectModel projectModel;
    private boolean isOn = false;
    private boolean isOn2 = false;
    private int onIcon = R.drawable.bellsalas;
    private int offIcon = R.drawable.bell;
    private int onCalend = R.drawable.calend20;
    private int offCalend = R.drawable.calendar_edit_outline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCalend = view.findViewById(R.id.textViewyved2);
        buttonEvents = view.findViewById(R.id.textViewyved1);
        AppCompatButton buttonNotify = view.findViewById(R.id.textViewyved2);
        //String lolipop = projectModel.getTitle();
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);

        isOn = sharedPreferences.getBoolean("isOn", false);

        isOn2 = sharedPreferences.getBoolean("isOn2", false);
        if (isOn) {
            buttonEvents.setBackgroundResource(onIcon);
            buttonEvents.setText("ON");
        } else {
            buttonEvents.setBackgroundResource(offIcon);
            buttonEvents.setText("OFF");
        }
        if (isOn2) {
            buttonCalend.setBackgroundResource(onCalend);
            buttonCalend.setTextColor(Color.parseColor("#00FF00"));
            buttonCalend.setText("ON");
        } else {
            buttonCalend.setBackgroundResource(offCalend);
            buttonCalend.setTextColor(Color.parseColor("#CE3335"));
            buttonCalend.setText("OFF");
        }
        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn = sharedPreferences.getBoolean("isOn", false);
                if (isOn) {
                    buttonEvents.setBackgroundResource(offIcon);
                    buttonEvents.setText("OFF");
                    sharedPreferences.edit().putBoolean("isOn", false).apply();
                } else {
                    buttonEvents.setBackgroundResource(onIcon);

                    buttonEvents.setText("ON");
                    sharedPreferences.edit().putBoolean("isOn", true).apply();
                }
            }
        });
        buttonCalend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOn2 = sharedPreferences.getBoolean("isOn2", false);
                if (isOn2) {
                    buttonCalend.setBackgroundResource(offCalend);
                    buttonCalend.setText("OFF");
                    buttonCalend.setTextColor(Color.parseColor("#CE3335"));
                    sharedPreferences.edit().putBoolean("isOn2", false).apply();
                } else {
                    buttonCalend.setBackgroundResource(onCalend);
                    buttonCalend.setText("ON");
                    buttonCalend.setTextColor(Color.parseColor("#00FF00"));
                    sharedPreferences.edit().putBoolean("isOn2", true).apply();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().putBoolean("isOn", isOn).apply();
        sharedPreferences.edit().putBoolean("isOn2", isOn2).apply();
    }

}

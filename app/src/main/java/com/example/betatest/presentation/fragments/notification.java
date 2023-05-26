package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;


import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.betatest.R;
import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.entity.SettModel;
import com.example.betatest.presentation.viewmodel.ProjectViewModel;

import java.util.Calendar;


public class notification extends Fragment {
    private AppCompatButton buttonEvents;
    private AppCompatButton buttonCalend;
    private ProjectViewModel projectViewModel;
    private int isOn;
    private int isOn2;
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
        projectViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ProjectViewModel.class);
        projectViewModel.getSettModelLiveData().observe(getViewLifecycleOwner(), projectModel -> {
            updateUI(projectModel);
                });


        buttonCalend = view.findViewById(R.id.textViewyved2);
        buttonEvents = view.findViewById(R.id.textViewyved1);


        AppCompatButton buttonNotify = view.findViewById(R.id.textViewyved2);
        //String lolipop = projectModel.getTitle();


        buttonCalend.setOnClickListener(v -> {
            ProjectModel projectModel= projectViewModel.getSettModelLiveData().getValue();
            if (projectModel != null) {
                if (projectModel.issues == 1) {
                    buttonCalend.setBackgroundResource(R.drawable.calendar_edit_outline);
                    buttonCalend.setTextColor(Color.parseColor("#CE3335"));
                    buttonCalend.setText("OFF");
                    projectModel.issues = 2;
                } else {
                    buttonCalend.setBackgroundResource(R.drawable.calend20);
                    buttonCalend.setTextColor(Color.parseColor("#00FF00"));
                    buttonCalend.setText("ON");
                    projectModel.issues = 1;
                }
                projectViewModel.updateProject(projectModel);
            }
        });

        buttonEvents.setOnClickListener(v -> {
            ProjectModel projectModel = projectViewModel.getSettModelLiveData().getValue();
            if (projectModel != null) {
                if (projectModel.issues3 == 1) {
                    buttonEvents.setBackgroundResource(R.drawable.bell);
                    buttonEvents.setText("OFF");
                    projectModel.issues3 = 2;
                } else {
                    buttonEvents.setBackgroundResource(R.drawable.bellsalas);
                    buttonEvents.setText("ON");
                    projectModel.issues3 = 1;
                }
                projectViewModel.updateProject(projectModel);
            }
        });
    }

    private void updateUI(ProjectModel projectModel) {
        if (projectModel != null) {
            if (projectModel.issues3 == 1) {
                buttonEvents.setBackgroundResource(R.drawable.bellsalas);
                buttonEvents.setText("ON");
            } else {
                buttonEvents.setBackgroundResource(R.drawable.bell);
                buttonEvents.setText("OFF");
            }

            if (projectModel.issues == 1) {
                buttonCalend.setBackgroundResource(R.drawable.calend20);
                buttonCalend.setTextColor(Color.parseColor("#00FF00"));
                buttonCalend.setText("ON");
            } else {
                buttonCalend.setBackgroundResource(R.drawable.calendar_edit_outline);
                buttonCalend.setTextColor(Color.parseColor("#CE3335"));
                buttonCalend.setText("OFF");
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

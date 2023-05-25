package com.example.betatest.presentation.fragments.MAP;

import com.example.betatest.data.storage.room.entity.IventsSpot;
import com.example.betatest.R;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betatest.data.storage.room.root.AppDatabase;
import com.example.betatest.presentation.viewmodel.iventsViewModel;
import com.google.firebase.database.DatabaseReference;
import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;

public class ivents extends Fragment {
    private iventsViewModel viewModel;
    private MapView mapView;
    ArrayList<IventsSpot> list;
    DatabaseReference database;
    public ivents() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(iventsViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ivents, container, false);
        viewModel = new ViewModelProvider(this).get(iventsViewModel.class);
        viewModel.initializeMap(requireContext());
        mapView = view.findViewById(R.id.mapview);
        viewModel.setMapView(mapView);
        viewModel.moveCameraToTargetLocation();
        GetProjectsAsynk task = new GetProjectsAsynk(AppDatabase.getInstance(requireContext()), requireContext(), mapView);
        task.execute();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
        viewModel.onStop();
    }
}




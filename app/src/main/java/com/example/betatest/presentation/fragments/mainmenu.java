package com.example.betatest.presentation.fragments;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.betatest.data.storage.room.entity.IventsSpot;
import com.example.betatest.OnClickItemInterface;
import com.example.betatest.R;
import com.example.betatest.presentation.adapters.FirebaseAdapter;
import com.example.betatest.presentation.adapters.ProjectAdapter;
import com.example.betatest.presentation.viewmodel.ProjectViewModel;
import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.databinding.FragmentMainmenuBinding;
import com.example.betatest.presentation.activity.AddProjectActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mainmenu extends Fragment implements OnClickItemInterface {
    private FragmentMainmenuBinding binding;
    private ProjectViewModel projectViewModel;
    private ProjectAdapter adapter;
    public final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 1;
    FirebaseAdapter firebaseAdapter;
    public RecyclerView recyclerView;
    RecyclerView recyclerViewfirebase;
    ArrayList<IventsSpot> list;
    DatabaseReference database;
    public mainmenu() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainmenu, container, false);
        recyclerView = view.findViewById(R.id.projectRecyclerView);
        return view;
    }

    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton kolokol = view.findViewById(R.id.kolokol);
        ImageButton addProject = view.findViewById(R.id.addProject);
        RecyclerView projectRecyclerView = view.findViewById(R.id.projectRecyclerView);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProjectAdapter(getContext(),this,this, null);
        projectRecyclerView.setAdapter(adapter);
        recyclerViewfirebase = view.findViewById(R.id.firebaseRecyclerView);
        database = FirebaseDatabase.getInstance().getReference("IventsSpot");
        recyclerViewfirebase.setHasFixedSize(true);
        recyclerViewfirebase.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        firebaseAdapter = new FirebaseAdapter(getContext(), list);
        recyclerViewfirebase.setAdapter(firebaseAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Очищаем список перед загрузкой новых данных
                list.clear();
                   for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   IventsSpot iventsSpot = dataSnapshot.getValue(IventsSpot.class);
                   list.add(iventsSpot);
               }
               firebaseAdapter.notifyDataSetChanged();
           }
            @Override
    public void onCancelled(@NonNull DatabaseError error) {
         }
     });
        int permissionStatus = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE);
        }
        MutableLiveData<Uri> imageUri2 = new MutableLiveData<>();
        imageUri2.setValue(null);
        addProject.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), AddProjectActivity.class));
        });
        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        projectViewModel.getAllProjectLive().observe(getViewLifecycleOwner(), projectModelList -> {
            if (projectModelList != null) {
                adapter.setProjects(projectModelList);
            }
        });
        if (getActivity() instanceof OnRecyclerViewCreatedListener) {
            ((OnRecyclerViewCreatedListener) getActivity()).onRecyclerViewCreated(projectRecyclerView);
        }
        kolokol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_mainmenuFragment_to_yveds);
            }
        });
    }
    @Override
    public void onClickItem(ProjectModel projectModel, boolean isEdit) {
        if (isEdit) {
            Intent intent = new Intent(getContext(), AddProjectActivity.class);
            intent.putExtra("model", projectModel);
            startActivity(intent);
        } else {
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
            String lolipop = projectModel.getTitle();
            databaseRef.child("IventsSpot").orderByChild("titleSpot").equalTo(lolipop)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                snapshot.getRef().removeValue();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
            projectViewModel.deleteProject(projectModel);
        }
    }
    public interface OnRecyclerViewCreatedListener {
        void onRecyclerViewCreated(RecyclerView recyclerView);
    }
}




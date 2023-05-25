package com.example.betatest.presentation.adapters;


import com.example.betatest.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betatest.OnClickItemInterface;

import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.databinding.ProjectItemLayoutBinding;

import java.util.List;
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    List<ProjectModel> projectModelList;
    private Context context;
    private Uri imageUri;
    private RecyclerView recyclerView;;
    private SharedPreferences sharedPreferences;
    private static final int PERMISSION_REQUEST_GALLERY= 1;
    private OnClickItemInterface onClickItemInterface;

    private MutableLiveData<Uri> uriLiveData;

    public ProjectAdapter(Context context, OnClickItemInterface onClickItemInterface, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.onClickItemInterface = onClickItemInterface;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.project_item_layout, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          if (projectModelList != null) {
            ProjectModel projectModel = projectModelList.get(position);
           holder.binding.setProjectModel(projectModel);
            holder.binding.setListener(onClickItemInterface);
            holder.binding.imgLogorifm.setImageURI(imageUri);
            holder.binding.invalidateAll();

        }



    }
    public void setProjects(List<ProjectModel> projects) {
        projectModelList = projects;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (projectModelList != null)
            return projectModelList.size();
        else
            return 0;
        // list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ProjectItemLayoutBinding binding;
        TextView titleSpot, titlewatcher;

        public ViewHolder(ProjectItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            ///titleSpot =itemView.findViewById(R.id.txtPName);
            //titlewatcher = itemView.findViewById(R.id.txtPName2);



        }
    }
}

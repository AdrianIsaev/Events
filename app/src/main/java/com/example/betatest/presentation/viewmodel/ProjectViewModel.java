package com.example.betatest.presentation.viewmodel;

import android.app.Application;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.betatest.data.repository.AppRepo;
import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.entity.SettModel;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectViewModel extends AndroidViewModel {
    private AppRepo appRepo;
    private LiveData<ProjectModel> mSettModel;
    public ProjectViewModel(@NonNull Application application) {
        super(application);
        appRepo = new AppRepo(application);
        mSettModel = appRepo.getSettModelLiveData2();
    }
    public void insertProject(ProjectModel projectModel) {
        appRepo.insertProject(projectModel);
    }
    public void updateProject(ProjectModel projectModel) {
        appRepo.updateProject(projectModel);
    }
    public void deleteProject(ProjectModel projectModel) {
        appRepo.deleteProject(projectModel);
    }

    public void updateProject2(SettModel settModel){appRepo.updateProject2(settModel);}
    public LiveData<ProjectModel> getSettModelLiveData2() {
        return mSettModel;
    }

    public List<ProjectModel> getAllProjectFuture() throws ExecutionException, InterruptedException {
        return appRepo.getAllProjectFuture();
    }
    public LiveData<List<ProjectModel>> getAllProjectLive() {
        return appRepo.getAllProjectLive();
    }

    public void selectImage(Uri uri) {
        imageUri.setValue(uri);
    }

    private MutableLiveData<Uri> imageUri = new MutableLiveData<>();

    public LiveData<Uri> getImageUri() {
        return imageUri;
    }
}

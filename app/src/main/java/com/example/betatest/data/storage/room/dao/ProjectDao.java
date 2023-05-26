package com.example.betatest.data.storage.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.entity.SettModel;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(ProjectModel projectModel);
    @Update
    void updateProject(ProjectModel projectModel);
    @Delete
    void deleteProject(ProjectModel projectModel);
    @Query("SELECT * FROM project")
    LiveData<List<ProjectModel>> getAllProjectsLive();
    @Query("SELECT * FROM project")
    List<ProjectModel> getAllProjectsFuture();
    @Query("SELECT * FROM project WHERE pId=:id")
    ProjectModel getProject(int id);
    @Query("DELETE FROM project")
    void deleteAllProjects();

    @Query("SELECT * FROM project")
    LiveData<ProjectModel> getProjectModelLiveData();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(SettModel settModel);
    @Update
    void updateProject(SettModel settModel);


    @Query("SELECT * FROM project2")
    LiveData<SettModel> getSettModelLiveData();

    @Delete
    void deleteProject(SettModel settModel);
    @Query("SELECT * FROM project2")
    LiveData<List<SettModel>> getAllProjectsLive2();
    @Query("SELECT * FROM project2")
    List<SettModel> getAllProjectsFuture2();
    @Query("DELETE FROM project2")
    void deleteAllProjects2();
}

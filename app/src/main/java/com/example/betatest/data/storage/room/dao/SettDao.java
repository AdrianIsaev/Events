package com.example.betatest.data.storage.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.betatest.data.storage.room.entity.SettModel;

import java.util.List;
@Dao
public interface SettDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(SettModel settModel);
    @Update
    void updateProject(SettModel settModel);
    @Delete
    void deleteProject(SettModel settModel);
    @Query("SELECT * FROM project2")
    LiveData<List<SettModel>> getAllProjectsLive();
    @Query("SELECT * FROM project2")
    List<SettModel> getAllProjectsFuture();
    @Query("DELETE FROM project2")
    void deleteAllProjects();
}


package com.example.betatest.data.storage.room.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "project")
public class ProjectModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int pId;
    @ColumnInfo(name = "p_title")
    public String title;
    public String language;
    public int watcher;
    public int issues;
    public int issues3;
    public String name;
    public String description;
    public String imgUrl;

    public ProjectModel() {
    }
    protected ProjectModel(Parcel in) {
        pId = in.readInt();
        title = in.readString();
        language = in.readString();
        watcher = in.readInt();
        issues = in.readInt();
        issues3 = in.readInt();
        name = in.readString();
        description = in.readString();
        imgUrl = in.readString();
    }

    public ProjectModel(String name, String description, String imgUrl) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pId);
        dest.writeString(title);
        dest.writeString(language);
        dest.writeInt(watcher);
        dest.writeInt(issues);
        dest.writeInt(issues3);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imgUrl);

    }
    public String getName(){return  name;}
    public int getLatitude(){
        return issues;
    }
    public String getLanguage(){return language;}
    public int getLongitude(){
        return issues3;
    }
    public String getTitle(){
        return title;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<ProjectModel> CREATOR = new Creator<ProjectModel>() {
        @Override
        public ProjectModel createFromParcel(Parcel in) {
            return new ProjectModel(in);
        }
        @Override
        public ProjectModel[] newArray(int size) {
            return new ProjectModel[size];
        }
    };
}

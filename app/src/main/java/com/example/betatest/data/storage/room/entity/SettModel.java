package com.example.betatest.data.storage.room.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "project2")
public class SettModel implements  Parcelable{
    @PrimaryKey(autoGenerate = true)

    public boolean joji;
    public int jiji;
    public int jiji2;
    public SettModel() {
    }
    protected SettModel(Parcel in) {
        jiji = in.readInt();
        jiji2 = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            joji = in.readBoolean();}
    }

        public SettModel(Boolean joji, Integer jiji, Integer jiji2) {
            this.joji = joji;
            this.jiji = jiji;
            this.jiji2 = jiji2;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(jiji2);
            dest.writeInt(jiji);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                dest.writeBoolean(joji);
            }
        }
        public Boolean getjoji(){return  joji;}
        public int getjiji(){
            return jiji;
        }
        public int getjiji2(){
            return jiji2;
        }

        @Override
        public int describeContents() {
            return 0;
        }
        public static final Creator<com.example.betatest.data.storage.room.entity.SettModel> CREATOR = new Creator<SettModel>() {
            @Override
            public com.example.betatest.data.storage.room.entity.SettModel createFromParcel(Parcel in) {
                return new com.example.betatest.data.storage.room.entity.SettModel(in);
            }
            @Override
            public com.example.betatest.data.storage.room.entity.SettModel[] newArray(int size) {
                return new com.example.betatest.data.storage.room.entity.SettModel[size];
            }
        };
    }

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

    public int joji;
    public int joji2;
    public int jiji;
    public int jiji2;
    public SettModel() {
    }
    protected SettModel(Parcel in) {
        jiji = in.readInt();
        jiji2 = in.readInt();

            joji = in.readInt();

            joji2 = in.readInt();
    }

        public SettModel(Integer joji,Integer joji2, Integer jiji, Integer jiji2) {
            this.joji = joji;
            this.jiji = jiji;
            this.jiji2 = jiji2;
            this.joji2 = joji2;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(jiji2);
            dest.writeInt(jiji);
                dest.writeInt(joji);

                dest.writeInt(joji2);
        }
        public Integer getjoji(){return  joji;}
    public Integer getjoji2(){return  joji2;}
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

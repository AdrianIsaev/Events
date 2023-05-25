package com.example.betatest.presentation.fragments.MAP;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.example.betatest.R;
import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.root.AppDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.PlacemarkMapObject;

import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetProjectsAsynk extends AsyncTask<Void, Void, List<ProjectModel>> {
    private AppDatabase appDatabase;
    private Context context;
    private MapView mapView;
    public GetProjectsAsynk(AppDatabase appDatabase, Context context, MapView mapView) {
        this.appDatabase = appDatabase;
        this.context = context;
        this.mapView = mapView;
    }
    @Override
    protected List<ProjectModel> doInBackground(Void... voids) {
        return appDatabase.projectDao().getAllProjectsFuture();
    }
    @Override
    protected void onPostExecute(List<ProjectModel> projects) {
        Map map = mapView.getMap();
        for (ProjectModel project : projects) {
            String address = project.getName();
            String text = project.getTitle();
            Geocoder geocoder2 = new Geocoder(context, Locale.getDefault());
            try{
                List<Address> addresses = geocoder2.getFromLocationName(address, 1);
            if (addresses.size() > 0) {
                Address location = addresses.get(0);
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Point point = new Point(latitude, longitude);
                PlacemarkMapObject placemark = map.getMapObjects().addPlacemark(point);
                placemark.setTextStyle(new TextStyle().setColor(Color.BLACK));
                placemark.setOpacity(1.5f);
                placemark.setIcon(ImageProvider.fromResource(context, R.drawable.starrrr2));
                placemark.setText(text);
                placemark.setDraggable(true);
                map.move(new CameraPosition(point, 15.0f, 0.0f, 0.0f));
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("IventsSpot");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String address = childSnapshot.child("adressSpot").getValue(String.class);
                    String title = childSnapshot.child("titleSpot").getValue(String.class);
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocationName(address, 1);
                        if (addresses.size() > 0) {
                            Address location = addresses.get(0);
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Point point = new Point(latitude, longitude);
                            PlacemarkMapObject placemark = map.getMapObjects().addPlacemark(point);
                            placemark.setTextStyle(new TextStyle().setColor(Color.BLACK));
                            placemark.setOpacity(1.5f);
                            placemark.setIcon(ImageProvider.fromResource(context, R.drawable.starrrr2));
                            placemark.setText(title);
                            placemark.setDraggable(true);
                            map.move(new CameraPosition(point, 15.0f, 0.0f, 0.0f));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(eventListener);

    }
}
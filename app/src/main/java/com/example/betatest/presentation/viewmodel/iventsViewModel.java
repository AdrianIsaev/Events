package com.example.betatest.presentation.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.betatest.data.storage.room.entity.ProjectModel;
import com.example.betatest.data.storage.room.root.AppDatabase;
import com.yandex.mapkit.Animation;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;

import java.util.List;

public class iventsViewModel extends ViewModel {
    private AppDatabase appDatabase;
    public iventsViewModel() {
    }
    public iventsViewModel(Application application) {
        appDatabase = AppDatabase.getInstance(application);
    }
    private final String MAPKIT_API_KEY = "4f4b1009-668e-4a48-9fed-165e86e8f8ba";
    private final Point TARGET_LOCATION = new Point(55.751574, 37.573856);
    private MapView mapView;
    public void initializeMap(Context context) {
        MapKitFactory.initialize(context.getApplicationContext());
    }
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
    public void moveCameraToTargetLocation() {
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }
    public void addPlacemarkToMap() {
        PlacemarkMapObject placemark = mapView.getMap().getMapObjects().addPlacemark(TARGET_LOCATION);
        placemark.setOpacity(0.5f);
        placemark.setText("hbsiufybaiolfgbeailo;gbaerbgiaeb;ebg");
        placemark.setDraggable(true);
    }
    public void onStart() {
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }
     public void setPlacemarksFromDatabase(Context context, MapView mapView) {
        List<ProjectModel> projects = AppDatabase.getInstance(context).projectDao().getAllProjectsFuture();
        for (ProjectModel project : projects) {
            double latitude = project.getLatitude();
          double longitude = project.getLongitude();
            Point location = new Point(latitude, longitude);
              PlacemarkMapObject placemark = this.mapView.getMap().getMapObjects().addPlacemark(location);
              placemark.setOpacity(0.5f);
        placemark.setText(project.getTitle());
           placemark.setDraggable(true);
        }
    }
}


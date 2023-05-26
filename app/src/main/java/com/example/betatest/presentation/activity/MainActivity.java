package com.example.betatest.presentation.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;



import com.example.betatest.R;
import com.example.betatest.data.storage.room.root.AppDatabase;
import com.example.betatest.presentation.fragments.defaultfragment;
import com.yandex.mapkit.MapKitFactory;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        MapKitFactory.setApiKey("4f4b1009-668e-4a48-9fed-165e86e8f8ba");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}

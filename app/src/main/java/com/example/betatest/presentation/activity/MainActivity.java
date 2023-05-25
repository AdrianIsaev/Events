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
    private static final int REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 123;
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int PICK_IMAGE_REQUEST = 4;
    private View fragmentView;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, request it from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_MEDIA);
        } else {
            // Permission is granted, do what you need with media
            Toast.makeText(this, "00000000000000000000000000", Toast.LENGTH_LONG).show();
        }

        MapKitFactory.setApiKey("4f4b1009-668e-4a48-9fed-165e86e8f8ba");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        defaultfragment fragmentContainerView2 = (defaultfragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    // Do what you need with media

                } else {

                    // Permission denied
                    // Handle this case, e.g. disable media functionality
                }
                return;
            }
        }
    }
}

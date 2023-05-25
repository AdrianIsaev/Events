package com.example.betatest.presentation.activity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betatest.OnClickItemInterface;
import com.example.betatest.R;

import com.example.betatest.databinding.ActivityAddProjectBinding;
import com.example.betatest.presentation.adapters.ProjectAdapter;
import com.example.betatest.presentation.viewmodel.ProjectViewModel;
import com.example.betatest.data.storage.room.entity.ProjectModel;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AddProjectActivity extends AppCompatActivity implements OnClickItemInterface {

    private RecyclerView recyclerView;
    private static final int PERMISSION_REQUEST_GALLERY = 101;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityAddProjectBinding binding;
    private String title, lang, imgLogo, adress, description;
    private int watcher;
    private String language;

    private ProjectModel projectModel;
    private boolean isEdit = false;
    private ProjectAdapter adapter;
    public MutableLiveData<Uri> uriLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        ProjectViewModel projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);


        Geocoder geocoder = new Geocoder(getApplication(), new Locale("ru"));

        //RecyclerView recyclerView = fragment.recyclerView;

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this, this, this);
        //recyclerView.setAdapter(adapter);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();


        binding.BTNlogo.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), PICK_IMAGE_REQUEST);

        });


        if (getIntent().hasExtra("model")) {
            projectModel = getIntent().getParcelableExtra("model");
            binding.edtTitle.setText(projectModel.title);
            binding.edtIssue.setText(projectModel.description);
           // binding.edtLanguage.setText("777");

            binding.date.setText(projectModel.language);


            binding.edtIssue10.setText(projectModel.name);
            //binding.edtIssue.setText(String.valueOf(projectModel.issues));
            //binding.edtIssue2.setText(String.valueOf(projectModel.issues3));
            binding.edtWatcher.setText(String.valueOf(projectModel.watcher));

            isEdit = true;
        }

        binding.btnAddProject.setOnClickListener(view -> {
            if (isEdit) {
                title = binding.edtTitle.getText().toString().trim();
                watcher = Integer.parseInt(binding.edtWatcher.getText().toString().trim());
                description = binding.edtIssue.getText().toString().trim();
                adress = binding.edtIssue10.getText().toString().trim();


                String inputDate = binding.date.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dateFormat.setLenient(false);
                try {
                    Date date = dateFormat.parse(inputDate);
                    String formattedDate = dateFormat.format(date);
                    projectModel.language=formattedDate;
                    // formattedDate будет содержать дату в формате "гггг.мм.дд"
                } catch (ParseException e) {
                    Toast.makeText(this, "Была введена некорректная дата, требуемый формат: дд.мм.гггг", Toast.LENGTH_LONG).show();
                }



                try {
                    List<Address> addresses = geocoder.getFromLocationName(adress, 1);
                    if (addresses.size() > 0) {
                        projectModel.name = adress;
                    } else {
                        Toast.makeText(this, "Был введен некорректный адрес", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    // Произошла ошибка, обработка исключения
                }

                projectModel.description = description;
                projectModel.title = title;
                projectModel.watcher = watcher;

               // DatabaseReference iventsSpotRef = databaseRef.child("IventsSpot");

               // DatabaseReference myNewSpotRef = iventsSpotRef.push();

               // myNewSpotRef.child("titleSpot").setValue(title);
               // myNewSpotRef.child("usersSpot").setValue(watcher);
               // myNewSpotRef.child("adressSpot").setValue(adress);
               // myNewSpotRef.child("dateSpot").setValue(projectModel.language);



                projectViewModel.updateProject(projectModel);

                //Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                finish();
            } else {
                title = binding.edtTitle.getText().toString().trim();
                watcher = Integer.parseInt(binding.edtWatcher.getText().toString().trim());
                String jooooj = binding.edtWatcher.getText().toString().trim();
                description = binding.edtIssue.getText().toString().trim();
                adress = binding.edtIssue10.getText().toString().trim();


                projectModel = new ProjectModel();
                String inputDate = binding.date.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dateFormat.setLenient(false);
                try {
                    Date date = dateFormat.parse(inputDate);
                    String formattedDate = dateFormat.format(date);
                    projectModel.language = formattedDate;

                } catch (ParseException e) {
                    Toast.makeText(this, "Была введена некорректная дата, требуемый формат: дд.мм.гггг", Toast.LENGTH_LONG).show();
                }
                try {
                    List<Address> addresses = geocoder.getFromLocationName(adress, 1);
                    if (addresses.size() > 0) {
                        projectModel.name = adress;
                    } else {
                        Toast.makeText(this, "Был введен некорректный адрес", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                }

                projectModel.title = title;
                projectModel.description = description;
                projectModel.watcher = watcher;
                projectViewModel.insertProject(projectModel);
                DatabaseReference iventsSpotRef = databaseRef.child("IventsSpot");
                DatabaseReference myNewSpotRef = iventsSpotRef.push();
                myNewSpotRef.child("titleSpot").setValue(title);
                myNewSpotRef.child("usersSpot").setValue(jooooj);
                myNewSpotRef.child("adressSpot").setValue(adress);
                myNewSpotRef.child("dateSpot").setValue(projectModel.language);

                FirebaseStorage storage = FirebaseStorage.getInstance();
                ImageView imageView = findViewById(R.id.edtImgLogo);
                    try {
                        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        StorageReference storageRef = storage.getReference().child("images/" + UUID.randomUUID().toString() + ".jpg");
                        UploadTask uploadTask = storageRef.putBytes(data);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl = uri.toString();
                                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
                                        Query query = databaseRef.child("IventsSpot").orderByChild("titleSpot").equalTo(title);
                                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    snapshot.child("imageSpot").getRef().setValue(imageUrl);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Не установлена картинка", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }

        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Uri uri = data.getData();
            uriLiveData.setValue(uri);
            ProjectViewModel projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);
            projectViewModel.selectImage(uri);
            try {
                binding.edtImgLogo.setImageURI(uri);
                imgLogo = getRealPathFromURI(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    private String getRealPathFromURI(Uri uri) throws IOException {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClickItem(ProjectModel projectModel, boolean isEdit) {
    }
}

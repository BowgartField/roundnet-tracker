package fr.hamchez.roundnettracker.ui.game;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.gridlayout.widget.GridLayout;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import fr.hamchez.roundnettracker.BuildConfig;
import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.customComponents.ScoreComponent;
import fr.hamchez.roundnettracker.models.Team;

public class GameActivity extends AppCompatActivity {

    LinearLayout teamComponent;

    int liveGameId;
    Team teamOne;
    Team teamTwo;
    GridLayout imageGridView;

    ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    String currentPhotoPath;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        liveGameId = (int) getIntent().getExtras().get("gameId");
        teamOne = (Team) getIntent().getExtras().get("teamOne");
        teamTwo = (Team) getIntent().getExtras().get("teamTwo");

        imageGridView = findViewById(R.id.imageGridView);
        teamComponent = findViewById(R.id.teamComponent);

        teamComponent.addView(new ScoreComponent(this, liveGameId, teamOne));
        teamComponent.addView(new ScoreComponent(this, liveGameId, teamTwo));


        cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {

                if (result.getResultCode() == Activity.RESULT_OK) {

                    //Get full size picture
                    File file = new File(currentPhotoPath);
                    addImageView(file);
                    galleryAddPic();

                }

            });

        new Thread(() -> {

            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES+"/"+liveGameId);
            handler.post(() -> getListFiles(storageDir).forEach(this::addImageView));

        }).start();

    }

    private void addImageView(File file){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
            if (bitmap != null) {

                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
                imageView.setLayoutParams(layoutParams);

                imageView.setOnClickListener((View v) -> {

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    Uri photoURI = FileProvider.getUriForFile(
                            this, "fr.hamchez.roundnettracker.fileprovider", file
                    );

                    intent.setDataAndType(photoURI, "image/*");
                    startActivity(intent);

                });

                imageGridView.addView(imageView);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                inFiles.add(file);
            }
        }
        return inFiles;
    }

    public void onTakePictureClick(View view){

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA },1);
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            System.out.println("Error when trying to create picture file");
        }

        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(
                    getApplicationContext(),
                    "fr.hamchez.roundnettracker.fileprovider",
                    photoFile
            );

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            cameraActivityResultLauncher.launch(takePictureIntent);
        }

    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES+"/"+liveGameId);

        File image = File.createTempFile(imageFileName,".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void onFinishedClick(View view){



    }

}
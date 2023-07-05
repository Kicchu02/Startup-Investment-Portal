package com.codebrewers.startupinvestmentportal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class PostIdeaActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE = 200;

    Button chooseImage, postIdea;
    EditText title, shortDesc, longDesc;

    DatabaseHandler databaseHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_idea);

        chooseImage = findViewById(R.id.buttonChooseImage);
        postIdea = findViewById(R.id.buttonPostIdea);
        title = findViewById(R.id.editTextTitle);
        shortDesc = findViewById(R.id.editTextShortDescription);
        longDesc = findViewById(R.id.editTextLongDescription);

        chooseImage.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/");
            i.setAction(Intent.ACTION_GET_CONTENT);

            launchSomeActivity.launch(i);
        });

        postIdea.setOnClickListener(v -> {
//            Image image = null;
//            String strTitle = String.valueOf(title.getText());
//            String strShortDesc = String.valueOf(shortDesc.getText());
//            String strLongDesc = String.valueOf(longDesc.getText());
//            String strEmail = LoginActivity.getEMAIL();
//
//            if (!checkFieldsValidity(strTitle, strShortDesc, strLongDesc)) {
//                Toast.makeText(PostIdeaActivity.this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (databaseHandler.postIdea(image, strTitle, strShortDesc, strLongDesc, strEmail)) {
//                Toast.makeText(PostIdeaActivity.this, "Posted New Idea", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                Toast.makeText(PostIdeaActivity.this, "Something Went Wrong!!!\nCouldn't Post Idea", Toast.LENGTH_SHORT).show();
//            }
            
            // TEMP
            if (databaseHandler.postIdea()) {
                Toast.makeText(this, "Temporary Idea Posted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Not Posted", Toast.LENGTH_SHORT).show();
            }
            ////
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK) {
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private static boolean checkFieldsValidity(String title, String shortDesc, String longDesc) {
        return !title.isEmpty() && !shortDesc.isEmpty() && !longDesc.isEmpty();
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
//                        imageView.setImageBitmap(
//                                selectedImageBitmap);
                    }
                }
            });
}
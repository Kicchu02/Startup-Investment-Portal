package com.codebrewers.startupinvestmentportal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class PostIdeaActivity extends AppCompatActivity {
    Button chooseImage, postIdea;
    EditText title, shortDesc, longDesc;
    ImageView imageView;

    DatabaseHandler databaseHandler;

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri
                            );
                            imageView.setImageBitmap(selectedImageBitmap);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_idea);

        chooseImage = findViewById(R.id.buttonChooseImage);
        postIdea = findViewById(R.id.buttonPostIdea);
        title = findViewById(R.id.editTextTitle);
        shortDesc = findViewById(R.id.editTextShortDescription);
        longDesc = findViewById(R.id.editTextLongDescription);
        imageView = findViewById(R.id.imageViewPreview);

        databaseHandler = new DatabaseHandler(PostIdeaActivity.this);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        postIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTitle = String.valueOf(title.getText());
                String strShortDesc = String.valueOf(shortDesc.getText());
                String strLongDesc = String.valueOf(longDesc.getText());
                
                if (!checkFieldsValidity(strTitle, strShortDesc, strLongDesc)) {
                    Toast.makeText(PostIdeaActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (databaseHandler.postIdea(strTitle, imageView, strShortDesc, strLongDesc, LoginActivity.getEMAIL())) {
                    Toast.makeText(PostIdeaActivity.this, "Idea Posted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostIdeaActivity.this, "Failed to Post Idea", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    private static boolean checkFieldsValidity(String title, String shortDesc, String longDesc) {
        return !title.isEmpty() && !shortDesc.isEmpty() && !longDesc.isEmpty();
    }
}
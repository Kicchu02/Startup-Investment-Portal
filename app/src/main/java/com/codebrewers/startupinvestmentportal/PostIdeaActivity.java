package com.codebrewers.startupinvestmentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostIdeaActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE = 200;

    Button chooseImage, postIdea;
    EditText shortDesc, longDesc;

    DatabaseHandler databaseHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_idea);

        chooseImage = findViewById(R.id.buttonChooseImage);
        postIdea = findViewById(R.id.buttonPostIdea);
        shortDesc = findViewById(R.id.editTextShortDescription);
        longDesc = findViewById(R.id.editTextLongDescription);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/");
                i.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(i, "Select Image"), SELECT_IMAGE);
            }
        });

        postIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image image = null;
                String strShortDesc = String.valueOf(shortDesc.getText());
                String strLongDesc = String.valueOf(longDesc.getText());
//                String strEmail = LoginActivity.getEMAIL();
                String strEmail = "chumma";

                if (databaseHandler.postIdea(image, strShortDesc, strLongDesc, strEmail)) {
                    Toast.makeText(PostIdeaActivity.this, "Posted New Idea", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PostIdeaActivity.this, "Something Went Wrong!!!\nCouldn't Post Idea", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK) {
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
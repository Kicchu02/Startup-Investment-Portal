package com.codebrewers.startupinvestmentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class DetailedIdeaActivity extends AppCompatActivity {
    public static Ideas IDEA;

    TextView title;
    ImageView imageView;
    TextView longDescription;
    TextView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_idea);

        title = findViewById(R.id.textViewTitle);
        imageView = findViewById(R.id.imageView);
        longDescription = findViewById(R.id.textViewLongDescription);
        contact = findViewById(R.id.textViewContact);

        title.setText(IDEA.getTitle());
        longDescription.setText(IDEA.getLongDescription());
        contact.setText(IDEA.getEmail() + "\n" + IDEA.getPhone());

        if (IDEA.getImage() == null) {
            return;
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IDEA.getImage());
        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        imageView.setImageBitmap(bitmap);
    }
}
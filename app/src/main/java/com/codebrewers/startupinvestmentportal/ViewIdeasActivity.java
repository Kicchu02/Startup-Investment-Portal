package com.codebrewers.startupinvestmentportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewIdeasActivity extends AppCompatActivity {
    Button newPost;
    ScrollView scrollView;
    LinearLayout linearLayout;

    DatabaseHandler databaseHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ideas);

        newPost = findViewById(R.id.buttonNewPost);
        scrollView = findViewById(R.id.scrollViewContents);
        linearLayout = findViewById(R.id.linearLayoutContents);

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewIdeasActivity.this, PostIdeaActivity.class));
            }
        });

//        ArrayList<Ideas> ideas = databaseHandler.getIdeas();
//
//        for (Ideas idea :
//                ideas) {
//            TextView textView = new TextView(this);
//            textView.setText(idea.getShortDescription());
//            textView.setTextSize(28);
//            linearLayout.addView(textView);

        ArrayList<String> ideas = new ArrayList<>();
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");
        ideas.add("something");

        for (String idea :
                ideas) {
            TextView textView = new TextView(this);
            textView.setText(idea);
            textView.setTextSize(42);
            linearLayout.addView(textView);
        }
    }
}

package com.codebrewers.startupinvestmentportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ViewIdeasActivity extends AppCompatActivity {
    Button newPost;
    ImageButton refresh;
    ScrollView scrollView;
    LinearLayout linearLayout;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ideas);

        newPost = findViewById(R.id.buttonNewPost);
        refresh = findViewById(R.id.imageButtonRefresh);
        scrollView = findViewById(R.id.scrollViewContents);
        linearLayout = findViewById(R.id.linearLayoutContents);

        databaseHandler = new DatabaseHandler(ViewIdeasActivity.this);

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewIdeasActivity.this, PostIdeaActivity.class));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        ArrayList<Ideas> ideas = databaseHandler.getIdeas();

        if (ideas == null || ideas.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setTextSize(28);
            textView.setText("No items to display");
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            linearLayout.addView(textView);

            return;
        }

        for (Ideas idea :
                ideas) {
            CardView cardView = new CardView(this);
            cardView.setCardElevation(10);
            cardView.setPadding(100, 100, 100, 100);
            cardView.setCardBackgroundColor(Color.parseColor("#80000000"));
            cardView.setRadius(10);
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(cardViewParams);

            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
            cardViewMarginParams.setMargins(20, 20, 20, 20);
            cardView.requestLayout();

            TextView textView = new TextView(this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(24);
            textView.setText(idea.getTitle() + "\n" + idea.getShortDescription());

            cardView.addView(textView);

            linearLayout.addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailedIdeaActivity.IDEA = idea;
                    startActivity(new Intent(ViewIdeasActivity.this, DetailedIdeaActivity.class));
                }
            });
        }
    }
}

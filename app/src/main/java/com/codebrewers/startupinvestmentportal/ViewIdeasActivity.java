package com.codebrewers.startupinvestmentportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

        databaseHandler = new DatabaseHandler(ViewIdeasActivity.this);

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewIdeasActivity.this, PostIdeaActivity.class));
            }
        });

//        ArrayList<Ideas> ideas = databaseHandler.getIdeas();

        ArrayList<Ideas> ideas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ideas.add(new Ideas(
                    i,
                    "Example Title",
                    "Example Short Description Lorem Ipsum",
                    "Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\n" +
                            "molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\n" +
                            "numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\n" +
                            "optio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\n" +
                            "obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\n" +
                            "nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\n" +
                            "tenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\n" +
                            "quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \n" +
                            "sapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\n" +
                            "recusandae alias error harum maxime adipisci amet laborum. Perspiciatis \n" +
                            "minima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \n" +
                            "quibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \n" +
                            "fugiat, temporibus enim commodi iusto libero magni deleniti quod quam \n" +
                            "consequuntur! Commodi minima excepturi repudiandae velit hic maxime\n" +
                            "doloremque. Quaerat provident commodi consectetur veniam similique ad \n" +
                            "earum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \n" +
                            "fugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \n" +
                            "suscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\n" +
                            "modi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \n" +
                            "totam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \n" +
                            "quasi aliquam eligendi, placeat qui corporis!",
                    "example.email@gmail.com"
            ));
        }

        if (ideas.isEmpty()) {
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
            cardView.setCardElevation(20);
            cardView.setPadding(20, 20, 20, 20);
            cardView.setCardBackgroundColor(Color.parseColor("#eeeeee"));
            cardView.setRadius(10);
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(cardViewParams);

            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
            cardViewMarginParams.setMargins(20, 20, 20, 20);
            cardView.requestLayout();

            TextView textView = new TextView(this);
            textView.setText(idea.getTitle() + "\n" + idea.getShortDescription());
            textView.setTextSize(24);
            textView.setTextColor(Color.BLACK);

            cardView.addView(textView);

            linearLayout.addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ViewIdeasActivity.this, DetailedIdeaActivity.class));
                }
            });
        }
    }
}

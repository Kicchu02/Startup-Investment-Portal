package com.codebrewers.startupinvestmentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    EditText email, password;
    Button login;
    TextView newUser;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);
        newUser = findViewById(R.id.textViewNewUser);

        databaseHandler = new DatabaseHandler(LoginActivity.this);

        login.setOnClickListener(view -> {
            String strEmail = String.valueOf(email.getText());
            String strPassword = String.valueOf(password.getText());

            if (!isValidEmail(strEmail) || !isValidPassword(strPassword)) {
                Toast.makeText(LoginActivity.this, "Please Enter Valid Email and Password", Toast.LENGTH_SHORT).show();
                return;
            }

//                Check record and display appropriate message
            if (databaseHandler.isValidLogin(strEmail, strPassword)) {
                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//                EMAIL = strEmail;
                startActivity(new Intent(LoginActivity.this, ViewIdeasActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        });



        newUser.setOnClickListener(v -> {
            email.setText("");
            password.setText("");
            Intent loginToRegister = new Intent(this, ViewIdeasActivity.class);
            startActivity(loginToRegister);
        });


    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    private static boolean isValidPassword(String password) {
        if(password.length() >= 4) {
            return true;
        }
        return false;
    }
}
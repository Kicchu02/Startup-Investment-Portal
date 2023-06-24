package com.codebrewers.startupinvestmentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, password, rePassword, contact;
    Button register;
    TextView registeredUser;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        rePassword = findViewById(R.id.editTextReTypePassword);
        contact = findViewById(R.id.editTextContactNumber);
        register = findViewById(R.id.buttonRegister);
        registeredUser = findViewById(R.id.textViewRegisteredUser);
        
        databaseHandler = new DatabaseHandler(SignUpActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Insert Record into the database

                String strName = String.valueOf(name.getText());
                String strEmail = String.valueOf(email.getText());
                String strPassword = String.valueOf(password.getText());
                String strRePassword = String.valueOf(rePassword.getText());
                String strContact = String.valueOf(contact.getText());

                if (!isValidName(strName) || !isValidEmail(strEmail) || !isValidPassword(strPassword, strRePassword) || !isValidContact(strContact)) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (databaseHandler.registerUser(strName, strEmail, strPassword, strContact)) {
                    Toast.makeText(SignUpActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Error Occurred. Register Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                name.setText("");
                email.setText("");
                password.setText("");
                rePassword.setText("");
                contact.setText("");

                finish();
            }
        });

        registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                password.setText("");
                rePassword.setText("");
                contact.setText("");

                finish();
            }
        });
    }

    private static boolean isValidName(String name) {
        return name.length() != 0;
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

    private static boolean isValidPassword(String password, String rePassword) {
        if(password.length() >= 4 && password.equals(rePassword)) {
            return true;
        }
        return false;
    }

    private static boolean isValidContact(String contact) {
        return contact.length() == 10;
    }
}
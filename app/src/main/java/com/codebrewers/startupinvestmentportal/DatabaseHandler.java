package com.codebrewers.startupinvestmentportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "startup_investment_portal.db";
    private static final int DB_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryForUserTable = "CREATE TABLE users(" +
                "email TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "name TEXT," +
                "contact BIGINT" +
                ");";

        String queryForIdeasTable = "CREATE TABLE ideas(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image BLOB, " +
                "short_des TEXT, " +
                "long_des TEXT, " +
                "email TEXT, " +
                "FOREIGN KEY (email) REFERENCES users(email)" +
                ");";

        db.execSQL(queryForUserTable);
        db.execSQL(queryForIdeasTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users;");
        db.execSQL("drop table if exists ideas;");
        onCreate(db);
    }

    public boolean isValidLogin(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * " +
                "FROM users " +
                "WHERE email = ? AND password = ?;";
        String[] strArgs = { email, password };
        Cursor cursor = db.rawQuery(query, strArgs);
        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }

    public boolean registerUser(String name, String email, String password, String contact) {
//        String query = "insert into users values(" +
//                email + "," + name + "," + password + "," + contact +
//                ");";
//
//        db.execSQL(query);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("name", name);
        values.put("password", password);
        values.put("contact", Long.valueOf(contact));

        long res = db.insert("users", null, values);

        db.close();

        return res != -1;
    }

    public boolean postIdea(Image image, String short_des, String long_des, String email) {
        return  false;
    }

    public ArrayList<Ideas> getIdeas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * " +
                "FROM ideas;";

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Ideas> ideas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ideas.add(new Ideas(
                        cursor.getInt(1),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ideas;
    }
}

package com.codebrewers.startupinvestmentportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
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
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
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

    public boolean postIdea(String title, ImageView image, String short_des, String long_des, String email) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("image", bytes);
        values.put("short_des", short_des);
        values.put("long_des", long_des);
        values.put("email", email);

        long res = db.insert("ideas", null, values);

        return res != -1;
    }

    public ArrayList<Ideas> getIdeas() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT i.title, i.image, i.short_des, i.long_des, i.email, u.contact " +
                "FROM ideas i, users u " +
                "WHERE i.email = u.email;";
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Ideas> ideas = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                ideas.add(new Ideas(
                        cursor.getString(0),
                        cursor.getBlob(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getLong(5)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ideas;
    }
}

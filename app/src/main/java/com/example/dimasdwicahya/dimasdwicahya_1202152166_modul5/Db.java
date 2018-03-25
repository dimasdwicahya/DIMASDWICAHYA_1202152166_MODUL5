package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by asus a456 on 25/03/2018.
 */

public class Db extends SQLiteOpenHelper {
    //deklarasi variabel yang akan digunakan
    Context mContext;
    SQLiteDatabase db;

    public static final String DB_NAME = "listtodo.db";
    public static final String TABLE_NAME = "daftartodo";
    public static final String col_1 = "todo";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";

    //Concstructore
    public Db(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    //ketika database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean inputData(Data list) {
        //mencocokkan kolom beserta nilainya
        ContentValues val = new ContentValues();
        val.put(col_1, list.getTodo());
        val.put(col_2, list.getDeskripsi());
        val.put(col_3, list.getPrioritas());
        long hasil = db.insert(TABLE_NAME, null, val);
        if (hasil == -1) {
            return false;
        } else {
            return true;
        }
    }

    //method untuk menghapus data pada database
    public boolean removeData(String ToDo) {
        return db.delete(TABLE_NAME, col_1 + "=\"" + ToDo + "\"", null) > 0;
    }

    //method untuk mengakses dan membaca data dari database
    public void readData(ArrayList<Data> daftar) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT todo, description, priority FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            daftar.add(new Data(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}

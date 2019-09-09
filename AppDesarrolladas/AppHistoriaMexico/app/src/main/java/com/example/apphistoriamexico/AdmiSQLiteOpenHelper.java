package com.example.apphistoriamexico;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdmiSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdmiSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BasesDeDatos) {
        BasesDeDatos.execSQL("Create table t_articulos(codigo int primary Key, nomArticulo text, precio real)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

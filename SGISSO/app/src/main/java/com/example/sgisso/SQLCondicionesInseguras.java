package com.example.sgisso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLCondicionesInseguras extends SQLiteOpenHelper {
    public SQLCondicionesInseguras(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table conSeguras (responsable text primary key,observador text,descripcion text,tipoRiesgo text,nivelRiesgo text,fecha date,hora time)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

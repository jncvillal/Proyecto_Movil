package com.example.sgisso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLIncidentes extends SQLiteOpenHelper {
    public SQLIncidentes(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table incidentes (apellido text primary key,nombre text,creador text,descripcion text,proceso text,tipoIncidente text,tipoTrabajador text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

package com.example.simuladoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "filmes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "filmes";

    public BancoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT," +
                "diretor TEXT," +
                "ano INTEGER," +
                "nota INTEGER," +
                "genero TEXT," +
                "cinema INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void inserir(Filme f) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();

        v.put("titulo", f.getTitulo());
        v.put("diretor", f.getDiretor());
        v.put("ano", f.getAno());
        v.put("nota", f.getNota());
        v.put("genero", f.getGenero());
        v.put("cinema", f.isCinema() ? 1 : 0);

        db.insert(TABLE, null, v);
    }

    public Cursor listar() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE, null);
    }

    public void excluir(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id=?", new String[]{String.valueOf(id)});
    }
}

package com.example.android.agenda.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.android.agenda.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gilmar on 01/06/2017.
 */

public class Repository extends SQLiteOpenHelper {

    public Repository(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pessoas(id INTEGER PRIMARY KEY, nome TEXT, endereco TEXT, telefone TEXT, email TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS pessoas";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Pessoa pessoa) {

        SQLiteDatabase db = getWritableDatabase();
        db.insert("pessoas", null, getContentValues(pessoa));

    }

    @NonNull
    private ContentValues getContentValues(Pessoa pessoa) {
        ContentValues dados = new ContentValues();

        dados.put("nome", pessoa.getNome());
        dados.put("endereco", pessoa.getEndereco());
        dados.put("telefone", pessoa.getTelefone());
        dados.put("email", pessoa.getEmail());
        dados.put("nota", pessoa.getNota());
        return dados;
    }

    public List<Pessoa> getPessoas() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from pessoas", null);

        List<Pessoa> pessoas = new ArrayList<>();
        while(cursor.moveToNext()){
            Pessoa pessoa = new Pessoa();
            pessoa.setID(cursor.getLong(cursor.getColumnIndex("id")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoa.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            pessoa.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            pessoa.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            pessoa.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            pessoas.add(pessoa);
        }

        cursor.close();

        return pessoas;
    }

    public void delete(Pessoa pessoa) {

        SQLiteDatabase db = getWritableDatabase();
        String[] parans = {pessoa.getID().toString()};
        db.delete("pessoas", " id = ?", parans);
    }

    public void update(Pessoa pessoa) {

        SQLiteDatabase db = getWritableDatabase();
        String[] parans = {pessoa.getID().toString()};
        db.update("pessoas", getContentValues(pessoa), "id = ?", parans);

    }

}

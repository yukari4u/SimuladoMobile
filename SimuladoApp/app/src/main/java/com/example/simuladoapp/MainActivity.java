package com.example.simuladoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTitulo, edtDiretor, edtAno;
    Spinner spinnerGenero;
    RatingBar rating;
    CheckBox checkCinema;
    Button btnSalvar;
    ListView lista;

    BancoHelper banco;
    ArrayAdapter<String> adapter;
    ArrayList<String> filmes;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtDiretor = findViewById(R.id.edtDiretor);
        edtAno = findViewById(R.id.edtAno);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        rating = findViewById(R.id.rating);
        checkCinema = findViewById(R.id.checkCinema);
        btnSalvar = findViewById(R.id.btnSalvar);
        lista = findViewById(R.id.lista);

        banco = new BancoHelper(this);

        ArrayAdapter<CharSequence> genAdapter = ArrayAdapter.createFromResource(
                this, R.array.generos, android.R.layout.simple_spinner_item);
        spinnerGenero.setAdapter(genAdapter);

        filmes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmes);
        lista.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> salvar());

        lista.setOnItemLongClickListener((parent, view, position, id) -> {
            banco.excluir(position + 1);
            listar();
            return true;
        });

        listar();
    }

    private void salvar() {
        Filme f = new Filme(
                edtTitulo.getText().toString(),
                edtDiretor.getText().toString(),
                Integer.parseInt(edtAno.getText().toString()),
                (int) rating.getRating(),
                spinnerGenero.getSelectedItem().toString(),
                checkCinema.isChecked()
        );

        banco.inserir(f);
        listar();
    }

    private void listar() {
        filmes.clear();

        Cursor c = banco.listar();
        while (c.moveToNext()) {
            String txt = c.getString(1) + " - " +
                    c.getInt(3) + " - " +
                    c.getInt(4) + "⭐ - " +
                    c.getString(5);
            filmes.add(txt);
        }

        adapter.notifyDataSetChanged();
    }
}
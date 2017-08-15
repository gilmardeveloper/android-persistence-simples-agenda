package com.example.android.agenda.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.agenda.R;
import com.example.android.agenda.model.Pessoa;
import com.example.android.agenda.repository.Repository;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private FloatingActionButton viewBtnAdd;
    private List<Pessoa> pessoas;
    private ListView listView;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listView = (ListView) findViewById(R.id.list_view_pessoas);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = (Pessoa) listView.getItemAtPosition(position);
                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
                intent.putExtra("pessoa", pessoa);
                startActivity(intent);
            }
        });


        viewBtnAdd = (FloatingActionButton) findViewById(R.id.btn_add_list_pessoas);
        viewBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {

        repository = new Repository(this);
        pessoas = repository.getPessoas();
        repository.close();
        listView = (ListView) findViewById(R.id.list_view_pessoas);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pessoas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem itemDelete = menu.add("Delete");

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo adMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Pessoa pessoa = (Pessoa) listView.getItemAtPosition(adMenuInfo.position);
                repository = new Repository(ListaActivity.this);
                repository.delete(pessoa);
                repository.close();
                showToast(pessoa.getNome() + " foi deletado!!!");
                loadList();

                return false;
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

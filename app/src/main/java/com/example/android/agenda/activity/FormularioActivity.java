package com.example.android.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.agenda.R;
import com.example.android.agenda.repository.Repository;
import com.example.android.agenda.model.Pessoa;

public class FormularioActivity extends AppCompatActivity {

    private PessoaHelper pessoaHelper;
    private Repository repository;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        pessoaHelper = new PessoaHelper(this);

        Intent intent = getIntent();
        pessoa = (Pessoa) intent.getSerializableExtra("pessoa");
        if(pessoa != null){
            pessoaHelper.setPessoa(pessoa);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_formulario_ok) {

            pessoa = pessoaHelper.getPessoa();
            repository = new Repository(this);

            if(pessoa.getID() == null) {
                repository.insert(pessoa);
                showToast(pessoaHelper.getPessoa().getNome() + " foi adicionado com sucesso!!!");
            }else{
                repository.update(pessoa);
                showToast(pessoaHelper.getPessoa().getNome() + " foi alterado com sucesso!!!");
            }

            repository.close();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

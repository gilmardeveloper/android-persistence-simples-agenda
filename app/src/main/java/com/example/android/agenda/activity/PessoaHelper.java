package com.example.android.agenda.activity;

import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.agenda.R;
import com.example.android.agenda.model.Pessoa;

/**
 * Created by Gilmar on 01/06/2017.
 */

class PessoaHelper {

    private TextView viewNome;
    private TextView viewEndereco;
    private TextView viewTelefone;
    private TextView viewEmail;
    private RatingBar viewNota;
    private FormularioActivity formulario;
    private Pessoa pessoa;

    public PessoaHelper(FormularioActivity formulario) {
        this.formulario = formulario;
        initComponents();
    }

    private void initComponents() {
        pessoa = new Pessoa();

        viewNome = (TextView) formulario.findViewById(R.id.text_formulario_nome);
        viewEndereco = (TextView) formulario.findViewById(R.id.text_formulario_endereco);
        viewTelefone = (TextView) formulario.findViewById(R.id.text_formulario_telefone);
        viewEmail = (TextView) formulario.findViewById(R.id.text_formulario_email);
        viewNota = (RatingBar) formulario.findViewById(R.id.rating_formulario_nota);
    }

    public Pessoa getPessoa() {
        pessoa.setNome(viewNome.getText().toString());
        pessoa.setEndereco(viewEndereco.getText().toString());
        pessoa.setTelefone(viewTelefone.getText().toString());
        pessoa.setEmail(viewEmail.getText().toString());
        pessoa.setNota((double) viewNota.getProgress());
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        viewNome.setText(pessoa.getNome());
        viewEndereco.setText(pessoa.getEndereco());
        viewTelefone.setText(pessoa.getTelefone());
        viewEmail.setText(pessoa.getEmail());
        viewNota.setProgress(pessoa.getNota().intValue());
        this.pessoa = pessoa;
    }
}

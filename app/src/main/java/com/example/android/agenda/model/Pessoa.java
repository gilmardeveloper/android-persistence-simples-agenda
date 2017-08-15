package com.example.android.agenda.model;

import java.io.Serializable;

/**
 * Created by Gilmar on 01/06/2017.
 */

public class Pessoa implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long ID;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private Double nota;

    public Pessoa() {

    }

    public Pessoa(String nome, String endereco, String telefone, String email, Double nota) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.nota = nota;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return ID != null ? ID.equals(pessoa.ID) : pessoa.ID == null;

    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getID() + " - " + getNome();
    }
}

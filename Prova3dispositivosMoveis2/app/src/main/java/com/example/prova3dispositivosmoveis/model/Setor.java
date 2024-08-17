package com.example.prova3dispositivosmoveis.model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Setor implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="descricao")
    private String descricao;

    @Ignore
    private List<Produto> prods;

    public Setor() { }

    @Ignore
    public Setor(String descr) {
        descricao = descr;
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProds() {
        return prods;
    }

    public void setProds(List<Produto> prods) {
        this.prods = prods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return id == setor.id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return descricao;
    }
}

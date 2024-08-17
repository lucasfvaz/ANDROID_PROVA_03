package com.example.prova3dispositivosmoveis.model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class ListaCompras implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;

    private int prioridade;

    private double valorEstimado;

    @Ignore
    private List<Item> itens;

    public ListaCompras(){}

    public ListaCompras(String nome, int prioridade){
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public ListaCompras(String nome, int prioridade, double valorEstimado) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.valorEstimado = valorEstimado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaCompras that = (ListaCompras) o;
        return id == that.id && prioridade == that.prioridade && Double.compare(that.valorEstimado, valorEstimado) == 0 && Objects.equals(nome, that.nome) && Objects.equals(itens, that.itens);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    @NonNull
    public String toString() {
        return  "Nome: " + nome  +
                " | Prioridade: " + prioridade +
                " | Valor Estimado: " + valorEstimado;
    }
}

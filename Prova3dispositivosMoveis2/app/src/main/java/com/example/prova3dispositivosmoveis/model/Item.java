package com.example.prova3dispositivosmoveis.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double quantidade;

    private boolean comprado;

    @ColumnInfo(name = "idListaCompras")
    private int idListaCompras;

    @ColumnInfo(name = "idProduto")
    private int idProduto;

    public Item() { }

    public Item(double quantidade, boolean comprado, int idListaCompras, int idProduto) {
        this.quantidade = quantidade;
        this.comprado = comprado;
        this.idListaCompras = idListaCompras;
        this.idProduto = idProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

    public int getIdListaCompras() {
        return idListaCompras;
    }

    public void setIdListaCompras(int idListaCompras) {
        this.idListaCompras = idListaCompras;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        String c;
        if (comprado){
            c = "SIM";
        } else {
            c = "NÃO";
        }
        return "Quantidade: " + quantidade +
                "| Comprado: " + c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

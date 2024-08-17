package com.example.prova3dispositivosmoveis.database;

import android.annotation.SuppressLint;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.prova3dispositivosmoveis.model.Item;
import com.example.prova3dispositivosmoveis.model.ListaCompras;
import com.example.prova3dispositivosmoveis.model.Produto;
import com.example.prova3dispositivosmoveis.model.Setor;

@SuppressLint("RestrictedApi")
@Database(entities = {Setor.class, Produto.class, ListaCompras.class, Item.class},
        version = 3,
        exportSchema = true
)
public abstract class Banco extends RoomDatabase {
    public abstract SetorDAO setorDao();
    public abstract ProdutoDAO produtoDao();
    public abstract ListaComprasDAO listaComprasDAO();
    public abstract ItemsDAO itemsDAO();


}

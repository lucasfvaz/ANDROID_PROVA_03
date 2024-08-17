package com.example.prova3dispositivosmoveis.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prova3dispositivosmoveis.model.ListaCompras;

import java.util.List;

@Dao
public interface ListaComprasDAO {
    @Insert
    public void inserir(ListaCompras lc);

    @Delete
    public void remover(ListaCompras lc);

    @Update
    public void alterar(ListaCompras lc);

    @Query("select * from listacompras")
    public LiveData<List<ListaCompras>> getAll();
}

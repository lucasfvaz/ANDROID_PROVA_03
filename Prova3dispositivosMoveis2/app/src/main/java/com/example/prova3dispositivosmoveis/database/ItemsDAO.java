package com.example.prova3dispositivosmoveis.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prova3dispositivosmoveis.model.Item;

import java.util.List;

@Dao
public interface ItemsDAO {
    @Insert
    public long inserir(Item i);

    @Delete
    public void remover(Item i);

    @Update
    public void alterar(Item i);

    @Query("select * from item where idProduto in (select id from produto where id_setor = :setor )")
    public LiveData<List<Item>> buscarPorSetor(int setor);

    @Query("select * from item where idListaCompras = :idListaCompras")
    public LiveData<List<Item>> buscarPorListaDeCompras(int idListaCompras);

    @Query("select * from item")
    public LiveData<List<Item>> getAll();
}

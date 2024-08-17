package com.example.prova3dispositivosmoveis.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.prova3dispositivosmoveis.model.Setor;

import java.util.List;

@Dao
public interface SetorDAO {

    @Query("select * from setor")
    public LiveData<List<Setor>> getAll();

    @Query("select * from setor where descricao like :descricao")
    public LiveData<List<Setor>> findByDescricao(String descricao);

    @Insert
    public long inserir(Setor s);

    @Delete
    public int remover(Setor s);

    @Transaction
    @Delete
    public int removerVarios(List<Setor> setores);

    @Update
    public void atualizar(Setor s);


}

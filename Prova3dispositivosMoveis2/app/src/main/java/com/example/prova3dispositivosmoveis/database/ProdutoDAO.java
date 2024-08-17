package com.example.prova3dispositivosmoveis.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prova3dispositivosmoveis.model.Produto;

import java.util.List;

@Dao
public interface ProdutoDAO {
    @Insert
    public void inserir(Produto p);

    @Delete
    public void remover(Produto p);

    @Update
    public void alterar(Produto p);

    @Query("select * from produto where id_setor = :setor")
    public LiveData<List<Produto>> buscarPorSetor(int setor);

}

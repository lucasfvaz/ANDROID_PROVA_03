package com.example.prova3dispositivosmoveis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.example.prova3dispositivosmoveis.database.Banco;
import com.example.prova3dispositivosmoveis.database.ProdutoDAO;
import com.example.prova3dispositivosmoveis.model.Produto;
import com.example.prova3dispositivosmoveis.model.Setor;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    Setor setor;
    Banco banco;
    ProdutoDAO prodDAO;
    ArrayList<Produto> produtos;
    ArrayAdapter<Produto> adapter;
    ProdutoObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Intent it = getIntent();
        setor = (Setor) it.getSerializableExtra("setor");
        TextView txtSetor = (TextView) findViewById(R.id.txt_setor);
        txtSetor.setText( setor.getDescricao() );
        banco = Room.databaseBuilder(getApplicationContext(),
                Banco.class, "lojinha").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        prodDAO = banco.produtoDao();
        observer = new ProdutoObserver();
        produtos = new ArrayList<>();
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_single_choice,
                produtos);
        ListView lista = (ListView) findViewById(R.id.lista_produtos);
        lista.setAdapter( adapter );
        prodDAO.buscarPorSetor( setor.getId() ).observe(this, observer);
    }

    public void confirmar(View v) {
        String descr = ((EditText) findViewById(R.id.ed_produto))
                .getText().toString();
        double qtde = Double.parseDouble(
                ((EditText) findViewById(R.id.ed_qtde))
                .getText().toString());
        Produto prod = new Produto();
        prod.setDescricao( descr );
        prod.setPreco( qtde );
        prod.setIdSetor( setor.getId() );
        new Thread() {
            public void run() {
                Looper.prepare();
                prodDAO.inserir(prod);
                Looper.loop();
            }
        }.start();

    }

    class ProdutoObserver implements Observer<List<Produto>> {
        @Override
        public void onChanged(List<Produto> produtos) {
            ProdutoActivity.this.produtos.clear();
            ProdutoActivity.this.produtos.addAll(produtos);
            adapter.notifyDataSetChanged();
        }
    }
}
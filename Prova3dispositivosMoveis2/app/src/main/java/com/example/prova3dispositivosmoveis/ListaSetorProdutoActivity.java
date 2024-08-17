package com.example.prova3dispositivosmoveis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.example.prova3dispositivosmoveis.database.Banco;
import com.example.prova3dispositivosmoveis.database.SetorDAO;
import com.example.prova3dispositivosmoveis.model.Setor;

import java.util.ArrayList;
import java.util.List;

public class ListaSetorProdutoActivity extends AppCompatActivity {

    Banco bd;
    EditText edSetor;
    ListView listaSetores;
    SetorDAO sdao;
    List<Setor> setores;
    ArrayAdapter<Setor> adapter;
    Setor setorEdicao = null;
    SetoresObserver observador;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setorproduto);

        observador = new SetoresObserver();
        bd = Room.databaseBuilder( getApplicationContext(),
                Banco.class, "lojinha").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        sdao = bd.setorDao();
        setores = new ArrayList<>(100);
        edSetor = (EditText) findViewById(R.id.edSetor);
        listaSetores = (ListView) findViewById(R.id.listaSetores);

        adapter = new ArrayAdapter<Setor>(this,
                android.R.layout.simple_list_item_single_choice,
                setores);
        listaSetores.setAdapter( adapter );
        listaSetores.setChoiceMode( ListView.CHOICE_MODE_SINGLE);
        sdao.getAll().observe(this, observador);
    }

    public void inserir(View v) {
        final String nome = edSetor.getText().toString();
        if (!nome.trim().isEmpty()) {
            new Thread() {
                public void run() {
                    Looper.prepare();
                    if (setorEdicao == null) {
                        long id = sdao.inserir(new Setor(nome));
                        Toast.makeText(getApplicationContext(), "Inserido: " + id,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        setorEdicao.setDescricao( nome );
                        sdao.atualizar(setorEdicao);
                        setorEdicao = null;
                        Toast.makeText(getApplicationContext(), "Atualizado: ",
                                Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                }
            }.start();
            listaSetores.setItemChecked(-1, true);
        }
    }

    public void excluir(View v) {
        int pos = listaSetores.getCheckedItemPosition();
        if (pos >= 0) {
            Setor s = setores.get( pos );
            new Thread() {
                public void run() {
                    Looper.prepare();
                    sdao.remover( s );
                }
            }.start();
            listaSetores.setItemChecked(-1, true);
        }
    }

    public void editar(View v) {
        int pos = listaSetores.getCheckedItemPosition();
        if (pos >= 0) {
            setorEdicao = setores.get(pos);
            edSetor.setText(setorEdicao.getDescricao());
        }
    }

    public void produtos(View v) {
        int pos = listaSetores.getCheckedItemPosition();
        if (pos >= 0) {
            Setor setor = setores.get(pos);
            Intent it = new Intent(this, ProdutoActivity.class);
            it.putExtra("setor", setor);
            startActivity(it);
        }
    }

    public void filtrar(View v) {
        String nome = edSetor.getText().toString();
        if (nome != null) {
            nome = "%"+nome.trim()+"%";
            sdao.findByDescricao(nome).observe(this, observador);
        }
    }


    class SetoresObserver implements Observer<List<Setor>> {
        @Override
        public void onChanged( List<Setor> setors) {
            setores.clear();
            setores.addAll( setors );
            adapter.notifyDataSetChanged();
        }
    }
}
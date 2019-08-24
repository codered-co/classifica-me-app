package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;

public class DescricaoConquistaActivity extends AppCompatActivity {

    private TextView textViewDescricao;
    private ImageView imageViewDescricaoConquista;
    private TextView textViewNomeConquista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_conquista);

        textViewDescricao = findViewById(R.id.textView_descricao_conquista);
        imageViewDescricaoConquista = findViewById(R.id.imageView_conquista_descricao_conquista);
        textViewNomeConquista = findViewById(R.id.textView_nome_conquista_descricao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Descrição");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item1 = menu.findItem(R.id.action_ordenar);
        MenuItem item2 = menu.findItem(R.id.action_logout);
        MenuItem item3 = menu.findItem(R.id.action_editar);
        MenuItem item4 = menu.findItem(R.id.action_check);

        if (item1 != null) {
            item1.setVisible(false);
        } if (item2 != null) {
            item2.setVisible(false);
        } if (item3 != null) {
            item3.setVisible(false);
        }
        if (item4 != null) {
            item4.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}

package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;

public class DescricaoConquistaActivity extends AppCompatActivity {

    private TextView textViewDescricao;
    private ImageView imageViewDescricaoConquista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_conquista);

        textViewDescricao = findViewById(R.id.textView_descricao_conquista);
        imageViewDescricaoConquista = findViewById(R.id.imageView_conquista_descricao_conquista);
    }
}

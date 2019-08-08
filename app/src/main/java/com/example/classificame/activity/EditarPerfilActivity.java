package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.classificame.R;

public class EditarPerfilActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        floatingActionButtonEdit = findViewById(R.id.floatingActionButton_salvar_edit_perfil);
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditarPerfilActivity.this, "Edição salva! Tela apenas de representação, os campos vão mudar!", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

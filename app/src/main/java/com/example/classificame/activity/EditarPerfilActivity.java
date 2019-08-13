package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classificame.R;

public class EditarPerfilActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonEdit;
    private EditText editNome, editSobrenome, editGenero, editCidade, editEmail, editTelefone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        editNome = findViewById(R.id.editText_nome_edit_perfil);
        editSobrenome = findViewById(R.id.editText_sobrenome_edit_perfil);
        editGenero = findViewById(R.id.editText_genero_edit_perfil);
        editCidade = findViewById(R.id.editText_cidade_edit_perfil);
        editEmail = findViewById(R.id.editText_email_edit_perfil);
        editTelefone = findViewById(R.id.editText_telefone_edit_perfil);
        floatingActionButtonEdit = findViewById(R.id.floatingActionButton_salvar_edit_perfil);

        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void recuperarPerfil() {
        //Firebase
    }

    private void salvarAlteracao(){
        //Firebase
    }


}

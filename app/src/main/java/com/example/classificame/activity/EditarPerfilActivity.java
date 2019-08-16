package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.classificame.R;

public class EditarPerfilActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonEdit;
    private EditText editTextNome, editTextSobrenome, editTextGenero, editTextCidade, editTextEmail, editTextTelefone;
    private ImageView imageViewPerfilEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        editTextNome = findViewById(R.id.editText_nome_edit_perfil);
        editTextSobrenome = findViewById(R.id.editText_sobrenome_edit_perfil);
        editTextGenero = findViewById(R.id.editText_genero_edit_perfil);
        editTextCidade = findViewById(R.id.editText_cidade_edit_perfil);
        editTextEmail = findViewById(R.id.editText_email_edit_perfil);
        editTextTelefone = findViewById(R.id.editText_telefone_edit_perfil);
        floatingActionButtonEdit = findViewById(R.id.floatingActionButton_salvar_edit_perfil);
        imageViewPerfilEdit = findViewById(R.id.imageView_user_perfil);
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

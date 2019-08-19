package com.example.classificame.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.classificame.R;

public class AdicionarEmpresaActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDescricao, editTextRua,
            editTextBairro, editTextNumero, editTextCidade,
            editTextEstado,editTextCategoria, editTextTipo;
    private Button buttonSalvarEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_empresa);

        editTextNome = findViewById(R.id.editText_nome_empresa);
        editTextDescricao = findViewById(R.id.editText_descricao_empresa);
        editTextRua = findViewById(R.id.editText_rua_empresa);
        editTextBairro = findViewById(R.id.editText_bairro_empresa);
        editTextNumero = findViewById(R.id.editText_numero_empresa);
        editTextCidade = findViewById(R.id.editText_cidade_empresa);
        editTextEstado = findViewById(R.id.editText_estado_empresa);
        editTextCategoria = findViewById(R.id.editText_categoria_empresa);
        editTextTipo = findViewById(R.id.editText_tipo_empresa);
        buttonSalvarEmpresa = findViewById(R.id.button_salvar_empresa);


    }
}

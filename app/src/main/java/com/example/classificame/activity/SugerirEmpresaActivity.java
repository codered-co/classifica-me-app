package com.example.classificame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.classificame.R;

public class SugerirEmpresaActivity extends AppCompatActivity {

    private Spinner spinnerCategoria, spinnerLocal;
    private TextInputEditText editTextNomeEmpresa;
    private EditText editTextDescricaoEmpresa;
    private Button  buttonEnviarSugestao;
    private RadioGroup radioGroup;
    private RadioButton radioButton, radioButtonNull2;
    private String instituicao, local, categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerir_empresa);
        getSupportActionBar().setTitle("Sugestão");

        buttonEnviarSugestao = findViewById(R.id.button_enviar_sugestao);
        editTextNomeEmpresa = findViewById(R.id.editText_nome_empresa);
        editTextDescricaoEmpresa = findViewById(R.id.editText_descricao_empresa);

        //RadioGroup
        radioGroup = findViewById(R.id.radioGroup_Sugestao);
        radioButtonNull2 = findViewById(R.id.radioButton_null2);
        radioButtonNull2.setVisibility(View.GONE);

        //Spinner e Array Adapter
        spinnerLocal = findViewById(R.id.spinner_local);
        ArrayAdapter adapterLocal = ArrayAdapter.createFromResource(getApplicationContext(), R.array.local, android.R.layout.simple_list_item_1);
        spinnerLocal.setAdapter(adapterLocal);

        spinnerCategoria = findViewById(R.id.spinner_categoria);
        ArrayAdapter adapterCategoria = ArrayAdapter.createFromResource(getApplicationContext(), R.array.categoria, android.R.layout.simple_list_item_1);
        spinnerCategoria.setAdapter(adapterCategoria);

        buttonEnviarSugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSpinner();

                int id = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                instituicao = radioButton.getText().toString();

                String nomeEmpresa = editTextNomeEmpresa.getText().toString();
                String descricaoEmpresa = editTextDescricaoEmpresa.getText().toString();

                if (nomeEmpresa.isEmpty()) {
                    campoVazio(editTextNomeEmpresa);
                    return;
                } if (descricaoEmpresa.isEmpty()) {
                    campoVazio(editTextDescricaoEmpresa);
                    return;
                }  if(local.equals("(Local)")) {
                    Toast.makeText(SugerirEmpresaActivity.this, "Selecione um local", Toast.LENGTH_SHORT).show();
                    return;
                } if (categoria.equals("(Categoria)")) {
                    Toast.makeText(SugerirEmpresaActivity.this, "Selecione uma categoria", Toast.LENGTH_SHORT).show();
                }
                esconderTeclado();
                finish();
            }
        });
    }

    private void campoVazio(EditText campo) {
        campo.setError("Preencha este campo");
        campo.requestFocus();
    }

    private void esconderTeclado(){
        InputMethodManager inputManager = (InputMethodManager) SugerirEmpresaActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(buttonEnviarSugestao.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void getSpinner() {
        local = spinnerLocal.getSelectedItem().toString();
        categoria = spinnerCategoria.getSelectedItem().toString();
    }
    private void cadastrarSugestao() {
        //Firebase
    }
}

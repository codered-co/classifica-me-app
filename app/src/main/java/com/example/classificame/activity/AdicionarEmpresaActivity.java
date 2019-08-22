package com.example.classificame.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.classificame.R;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Empresa;
import com.example.classificame.model.Voto;

public class AdicionarEmpresaActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDescricao, editTextRua,
            editTextBairro, editTextNumero, editTextCidade,
            editTextEstado,editTextCategoria, editTextTipo,
            editTextCnpj, editTextTelefone;
    private Button buttonSalvarEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_empresa);

        getSupportActionBar().setTitle("Adicionar Empresa");

        editTextNome = findViewById(R.id.editText_nome_empresa);
        editTextDescricao = findViewById(R.id.editText_descricao_empresa);
        editTextRua = findViewById(R.id.editText_rua_empresa);
        editTextBairro = findViewById(R.id.editText_bairro_empresa);
        editTextNumero = findViewById(R.id.editText_numero_empresa);
        editTextCidade = findViewById(R.id.editText_cidade_empresa);
        editTextEstado = findViewById(R.id.editText_estado_empresa);
        editTextCategoria = findViewById(R.id.editText_categoria_empresa);
        editTextTipo = findViewById(R.id.editText_tipo_empresa);
        editTextCnpj = findViewById(R.id.editText_cnpj_empresa);
        editTextTelefone = findViewById(R.id.editText_telefone);
        buttonSalvarEmpresa = findViewById(R.id.button_salvar_empresa);

        buttonSalvarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String descricao = editTextDescricao.getText().toString();
                String rua = editTextRua.getText().toString();
                String bairro = editTextBairro.getText().toString();
                String numero = editTextNumero.getText().toString();
                String cidade = editTextCidade.getText().toString();
                String estado = editTextEstado.getText().toString().toUpperCase();
                String categoria = editTextCategoria.getText().toString();
                String tipo = editTextTipo.getText().toString();
                String cnpj = editTextCnpj.getText().toString();
                String telefone = editTextTelefone.getText().toString();

                esconderTeclado();

                Empresa empresa = new Empresa();
                Voto voto = new Voto(0.0, 0.0, 0.0, 0.0);

                empresa.setNome(nome);
                empresa.setDescricao(descricao);
                empresa.setRua(rua);
                empresa.setBairro(bairro);
                empresa.setNumero(numero);
                empresa.setCategoria(cidade);
                empresa.setEstado(estado);
                empresa.setCategoria(categoria);
                empresa.setTipo(tipo);
                empresa.setCnpj(cnpj);
                empresa.setTelefone(telefone);
                empresa.setVoto(voto);
                empresa.setTotalVotos(0);
                empresa.setClassificacao(0.0);

                salvarEmpresa(empresa);
            }
        });
    }

    private void esconderTeclado(){
        InputMethodManager inputManager = (InputMethodManager) AdicionarEmpresaActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(buttonSalvarEmpresa.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void salvarEmpresa(Empresa empresa){
        String idEmpresa = Base64Helper.codificarBase64(empresa.getCnpj());
        empresa.setId(idEmpresa);
        empresa.salvarEmpresa();
        startActivity(new Intent(AdicionarEmpresaActivity.this, ContainerActivity.class));
        finish();
    }
}

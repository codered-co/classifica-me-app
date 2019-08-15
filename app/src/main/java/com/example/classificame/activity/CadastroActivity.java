package com.example.classificame.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText editTextNome, editTextEmail, editTextSenha, editTextConfirmarSenha,
            editTextAno,editTextCidade, editTextEstado;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado, radioButtonNull;
    private Spinner spinnerDia, spinnerMes, spinnerPaises;
    private Button buttonCadastrar;

    private Locale[] locales = Locale.getAvailableLocales();
    private ArrayList<String> paises = new ArrayList<>();

    private Usuario usuario;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setTitle("Cadastro");

        auth = ConfigFirebase.getAuth();

        editTextNome = findViewById(R.id.editText_nome);
        editTextEmail = findViewById(R.id.editText_email);
        editTextSenha = findViewById(R.id.editText_senha);
        editTextConfirmarSenha = findViewById(R.id.editText_confirmar_senha);
        editTextAno = findViewById(R.id.editText_ano);
        editTextCidade = findViewById(R.id.editText_cidade);
        editTextEstado = findViewById(R.id.editText_estado);
        spinnerDia = findViewById(R.id.spinner_dia);
        spinnerMes = findViewById(R.id.spinner_mes);
        spinnerPaises = findViewById(R.id.spinner_paises);
        buttonCadastrar = findViewById(R.id.button_cadastrar);
        radioGroup = findViewById(R.id.radioGroup_sexo);
        radioButtonNull = findViewById(R.id.radioButton_null);

        radioButtonNull.setVisibility(View.GONE);

        if (auth.getCurrentUser() != null) {
            FirebaseUser user = auth.getCurrentUser();
            editTextNome.setText(user.getDisplayName());
            editTextEmail.setText(user.getEmail());
            editTextSenha.setVisibility(View.GONE);
            editTextConfirmarSenha.setVisibility(View.GONE);
        }

        configurarSpinner(spinnerDia, R.array.dia);
        configurarSpinner(spinnerMes, R.array.mes);
        configurarSpinnerPaises();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString().trim();
                String confirmarSenha = editTextConfirmarSenha.getText().toString().trim();
                String diaNascimento = spinnerDia.getSelectedItem().toString();
                String mesNascimento = spinnerMes.getSelectedItem().toString();
                String anoNascimento = editTextAno.getText().toString();
                String cidade = editTextCidade.getText().toString();
                String estado = editTextEstado.getText().toString().toUpperCase();
                String pais = spinnerPaises.getSelectedItem().toString();

                int id = radioGroup.getCheckedRadioButtonId();
                radioButtonSelecionado = findViewById(id);
                String sexo = radioButtonSelecionado.getText().toString();

                if (!nome.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!senha.isEmpty()|| auth.getCurrentUser() != null) {
                            if (!confirmarSenha.isEmpty()|| auth.getCurrentUser() != null) {
                                if (!anoNascimento.isEmpty()) {
                                    if (!cidade.isEmpty()) {
                                        if (!estado.isEmpty()) {
                                            if (!pais.isEmpty()) {
                                                if (!sexo.equals("")) {
                                                    if (confirmarSenha.equals(senha)) {
                                                        esconderTeclado();

                                                        usuario = new Usuario();
                                                        usuario.setNome(nome);
                                                        usuario.setEmail(email);
                                                        usuario.setSenha(senha);
                                                        usuario.setDiaNascimento(Integer.parseInt(diaNascimento));
                                                        usuario.setMesNascimento(mesNascimento);
                                                        usuario.setAnoNascimento(Integer.parseInt(anoNascimento));
                                                        usuario.setSexo(sexo);
                                                        usuario.setCidade(cidade);
                                                        usuario.setEstado(estado);
                                                        usuario.setPais(pais);

                                                        cadastrarUsuario();
                                                    } else {
                                                        editTextSenha.setError("Senhas não conferem");
                                                        editTextConfirmarSenha.setError("Senhas não conferem");
                                                        editTextSenha.requestFocus();
                                                    }
                                                } else {
                                                    Toast.makeText(CadastroActivity.this, "Selecione um Sexo.", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(CadastroActivity.this, "Selecione um País", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            campoVazio(editTextEstado);
                                        }
                                    } else {
                                        campoVazio(editTextCidade);
                                    }
                                } else {
                                    campoVazio(editTextAno);
                                }
                            } else {
                                campoVazio(editTextConfirmarSenha);
                            }
                        } else {
                            campoVazio(editTextSenha);
                        }
                    } else {
                        campoVazio(editTextEmail);
                    }
                } else {
                    campoVazio(editTextNome);
                }
            }
        });
    }

    private void configurarSpinner(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setAdapter(adapter);
    }

    private void configurarSpinnerPaises() {
        for (Locale locale : locales) {
            String pais = locale.getDisplayCountry();
            if (pais.trim().length() > 0 && !paises.contains(pais)) {
                paises.add(pais);
            }
        }

        Collections.sort(paises);

        ArrayAdapter<String> paisesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paises);
        paisesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPaises.setAdapter(paisesAdapter);
        spinnerPaises.setSelection(paisesAdapter.getPosition("Brasil"));
    }

    private void esconderTeclado(){
        InputMethodManager inputManager = (InputMethodManager) CadastroActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(buttonCadastrar.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void campoVazio(EditText campo) {
        campo.setError("Preencha este campo.");
        campo.requestFocus();
    }

    private void cadastrarUsuario() {
        if (auth.getCurrentUser() == null) {
            auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String idUsuario = Base64Helper.codificarBase64(usuario.getEmail());
                                usuario.setId(idUsuario);
                                usuario.salvarUsuario();
                                startActivity(new Intent(CadastroActivity.this, ContainerActivity.class));
                                finish();
                            } else {
                                String excecao = "";
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    editTextSenha.setError("Senha muito fraca");
                                    excecao = "Digite uma senha mais forte";
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    editTextEmail.setError("Email Inválido");
                                    excecao = "Por favor, digite um email valido";
                                } catch (FirebaseAuthUserCollisionException e) {
                                    editTextEmail.setError("Email existente");
                                    excecao = "Essa conta já existe";
                                } catch (Exception e) {
                                    excecao = "Erro ao cadastrar usuário" + e.getMessage();
                                    e.printStackTrace();
                                }
                                Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            salvarUsuario(usuario);
        }
    }

    private void salvarUsuario(Usuario usuario){
        String idUsuario = Base64Helper.codificarBase64(usuario.getEmail());
        usuario.setId(idUsuario);
        usuario.salvarUsuario();
        startActivity(new Intent(CadastroActivity.this, ContainerActivity.class));
        finish();
    }
}

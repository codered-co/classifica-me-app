package com.example.classificame.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextSenha;
    private Button buttonEntrar;

    private Usuario usuario;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        buttonEntrar = findViewById(R.id.button_entrar);
        editTextEmail = findViewById(R.id.editText_email);
        editTextSenha = findViewById(R.id.editText_senha);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString().trim();

                if (!email.isEmpty()){
                    if (!senha.isEmpty()){
                        esconderTeclado();

                        usuario = new Usuario();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        validarLogin();
                    } else {
                        campoVazio(editTextSenha);
                    }
                } else {
                    campoVazio(editTextEmail);
                }
            }
        });
    }

    private void esconderTeclado(){
        InputMethodManager inputManager = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(buttonEntrar.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void campoVazio(EditText campo) {
        campo.setError("Preencha este campo.");
        campo.requestFocus();
    }

    private void validarLogin(){
        auth = ConfigFirebase.getAuth();

        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                    finish();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou Senha inválidos";
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuario nao está cadastrado";
                    } catch (Exception e) {
                        excecao = "Erro";
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

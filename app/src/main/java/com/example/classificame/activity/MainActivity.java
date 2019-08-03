package com.example.classificame.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button buttonCadastrar, buttonLoginEmail;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        buttonCadastrar = findViewById(R.id.button_cadastrar);
        buttonLoginEmail = findViewById(R.id.button_login_email);

        //auth = ConfigFirebase.getAuth();
        //auth.signOut();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });

        buttonLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarLogin();
    }

    public void verificarLogin() {
        auth = ConfigFirebase.getAuth();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, ContainerActivity.class));
            finish();
        }
    }
}

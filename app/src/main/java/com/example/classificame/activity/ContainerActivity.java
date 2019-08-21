package com.example.classificame.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.fragment.ClassificarFragment;
import com.example.classificame.fragment.PerfilFragment;
import com.example.classificame.fragment.RankingFragment;
import com.example.classificame.helper.Base64Helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ContainerActivity extends AppCompatActivity {

    private ValueEventListener listener;
    private DatabaseReference firebase;
    private FirebaseAuth auth;

    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_classificar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new ClassificarFragment(), "classificar").commit();
                    return true;
                case R.id.navigation_perfil:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new PerfilFragment(), "perfil").commit();
                    return true;
                case R.id.navigation_ranking:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new RankingFragment(), "home").commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new RankingFragment()).commit();

        auth = ConfigFirebase.getAuth();
        firebase = ConfigFirebase.getDatabase();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("home");
        if (fragment != null && fragment.isVisible()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(ContainerActivity.this);
            alerta.setTitle("Atenção").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setNegativeButton("Não", null).setMessage("Deseja fechar o aplicativo?").show();

        } else {
            navView.setSelectedItemId(R.id.navigation_ranking);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarPerfil();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebase.removeEventListener(listener);
        }
    }

    private void verificarPerfil() {
        listener = firebase.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String idUsuario = "";
                if (auth.getCurrentUser().getEmail() != null) {
                    idUsuario = Base64Helper.codificarBase64(auth.getCurrentUser().getEmail());
                }

                if (!dataSnapshot.hasChild(idUsuario)) {
                    startActivity(new Intent(ContainerActivity.this, CadastroActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

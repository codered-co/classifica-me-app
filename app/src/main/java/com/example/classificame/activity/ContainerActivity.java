package com.example.classificame.activity;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.classificame.R;
import com.example.classificame.fragment.ClassificarFragment;
import com.example.classificame.fragment.PerfilFragment;
import com.example.classificame.fragment.RankingFragment;

public class ContainerActivity extends AppCompatActivity {

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
}

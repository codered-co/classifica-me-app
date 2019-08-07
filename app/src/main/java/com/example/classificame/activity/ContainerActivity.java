package com.example.classificame.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.classificame.R;
import com.example.classificame.fragment.ClassificarFragment;
import com.example.classificame.fragment.PerfilFragment;
import com.example.classificame.fragment.RankingFragment;

public class ContainerActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_classificar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new ClassificarFragment()).commit();
                    return true;
                case R.id.navigation_perfil:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new PerfilFragment()).commit();
                    return true;
                case R.id.navigation_ranking:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new RankingFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new ClassificarFragment()).commit();
    }

}

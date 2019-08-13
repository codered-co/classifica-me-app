package com.example.classificame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.activity.EditarPerfilActivity;
import com.example.classificame.activity.MainActivity;
import com.example.classificame.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */

public class PerfilFragment extends Fragment {

    private TextView textViewNome, textViewSobrenome, textViewCidade, textViewDataNascimento, textViewTipoConsumidor;
    private ProgressBar progressBarNivel;
    private ImageView imageViewPerfil, imageViewEmblema;

    public PerfilFragment() {
        //Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        textViewNome = view.findViewById(R.id.textView_nome_perfil);
        textViewSobrenome = view.findViewById(R.id.textView_sobrenome_perfil);
        textViewCidade = view.findViewById(R.id.textView_cidade_perfil);
        textViewTipoConsumidor = view.findViewById(R.id.textView_consumidor_perfil);
        textViewDataNascimento = view.findViewById(R.id.textView_data_perfil);
        progressBarNivel = view.findViewById(R.id.progressBar_perfil_user);
        imageViewPerfil = view.findViewById(R.id.imageView_perfil_usuario);
        imageViewEmblema = view.findViewById(R.id.imageView_emblema_perfil);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item1 = menu.findItem(R.id.action_ordenar);

        if (item1 != null) {
            item1.setVisible(false);
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            encerrarSessao();
        }
        if (id == R.id.action_editar) {
            Intent i = new Intent(getContext(), EditarPerfilActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void encerrarSessao() {
        FirebaseAuth auth = ConfigFirebase.getAuth();
        auth.signOut();
        getActivity().finish();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    private void recuperarPerfil() {
        //Firebase
    }
}

package com.example.classificame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.activity.EditarPerfilActivity;
import com.example.classificame.activity.MainActivity;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Usuario;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */

public class PerfilFragment extends Fragment {

    private TextView textViewNome, textViewCidade, textViewDataNascimento,
            textViewTipoConsumidor;
    private ProgressBar progressBarNivel;
    private ImageView imageViewPerfil, imageViewEmblema;

    private Usuario usuario;

    private FirebaseAuth auth;
    private DatabaseReference firebase;
    private ValueEventListener listener;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        auth = ConfigFirebase.getAuth();
        firebase = ConfigFirebase.getDatabase();

        textViewNome = view.findViewById(R.id.textView_nome_perfil);
        textViewCidade = view.findViewById(R.id.textView_cidade_perfil);
        textViewTipoConsumidor = view.findViewById(R.id.textView_consumidor_perfil);
        textViewDataNascimento = view.findViewById(R.id.textView_data_perfil);
        progressBarNivel = view.findViewById(R.id.progressBar_perfil_user);
        imageViewPerfil = view.findViewById(R.id.imageView_perfil_usuario);
        imageViewEmblema = view.findViewById(R.id.imageView_emblema_perfil);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarPerfil();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (listener != null) {
            firebase.removeEventListener(listener);
        }
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
        LoginManager.getInstance().logOut();
        getActivity().finish();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    private void recuperarPerfil() {
        String idUsuario = Base64Helper.codificarBase64(auth.getCurrentUser().getEmail());
        listener = firebase.child("usuario").child(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(Usuario.class);

                String dataNascimento = usuario.getDiaNascimento() + "/" +
                        switchMes(usuario.getMesNascimento()) + "/" +
                        usuario.getAnoNascimento();
                String local = usuario.getCidade() + " - " + usuario.getEstado();

                textViewNome.setText(usuario.getNome());
                textViewDataNascimento.setText(dataNascimento);
                textViewCidade.setText(local);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String switchMes(String mes) {
        String numeroMes = "";
        switch (mes){
            case "janeiro":
                numeroMes = "01";
                break;
            case "fevereiro":
                numeroMes = "02";
                break;
            case "março":
                numeroMes = "03";
                break;
            case "abril":
                numeroMes = "04";
                break;
            case "maio":
                numeroMes = "05";
                break;
            case "junho":
                numeroMes = "06";
                break;
            case "julho":
                numeroMes = "07";
                break;
            case "agosto":
                numeroMes = "08";
                break;
            case "setembro":
                numeroMes = "09";
                break;
            case "outubro":
                numeroMes = "10";
                break;
            case "novembro":
                numeroMes = "11";
                break;
            case "dezembro":
                numeroMes = "12";
                break;
        }
        return numeroMes;
    }
}

package com.example.classificame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.activity.AdicionarEmpresaActivity;
import com.example.classificame.activity.CadastroActivity;
import com.example.classificame.activity.EditarPerfilActivity;
import com.example.classificame.activity.MainActivity;
import com.example.classificame.adapter.AdapterGamificacao;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Gamificacao;
import com.example.classificame.model.Usuario;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class PerfilFragment extends Fragment {

    private TextView textViewNome, textViewDataNascimento, textViewTituloUsuarioGamificacao;

    private ImageView imageViewPerfil, imageViewEmblema;
    private Button buttonAdicionarEmpresa;

    private RecyclerView recyclerViewGamificacao;
    private ArrayList<Gamificacao> gamificacoes = new ArrayList<>();
    private AdapterGamificacao adapterGamificacao;

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

        textViewTituloUsuarioGamificacao = view.findViewById(R.id.textView_titulo_usuario_perfil);
        textViewNome = view.findViewById(R.id.textView_nome_perfil);
        textViewDataNascimento = view.findViewById(R.id.textView_data_perfil);

        imageViewPerfil = view.findViewById(R.id.imageView_perfil_usuario);
        imageViewEmblema = view.findViewById(R.id.imageView_emblema_perfil);
        buttonAdicionarEmpresa = view.findViewById(R.id.button_adicionar_empresa);

        buttonAdicionarEmpresa.setVisibility(View.GONE);

        buttonAdicionarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AdicionarEmpresaActivity.class));
            }
        });

        //RecyclerView
        recyclerViewGamificacao = view.findViewById(R.id.recyclerView_perfil_gamificacao);
        recyclerViewGamificacao.setLayoutManager(new LinearLayoutManager(getContext()));
        //Adapter
        adapterGamificacao = new AdapterGamificacao(gamificacoes, getContext());
        recyclerViewGamificacao.setAdapter(adapterGamificacao);

        criaGamificacao();
        return view;
    }

    public void criaGamificacao() {

        Gamificacao gamificacao1 = new Gamificacao();
        gamificacao1.setNomeConquista("Primeiro voto");
        gamificacao1.setImagemConquista(R.drawable.ic_votefirst);
        gamificacao1.setImagemDesbloquear(R.drawable.ic_lock);
        gamificacoes.add(gamificacao1);

        Gamificacao gamificacao2 = new Gamificacao();
        gamificacao2.setNomeConquista("Consumidor Engajado");
        gamificacao2.setImagemConquista(R.drawable.ic_engagedconsumer);
        gamificacao2.setImagemDesbloquear(R.drawable.ic_lock);
        gamificacoes.add(gamificacao2);

        Gamificacao gamificacao3 = new Gamificacao();
        gamificacao3.setNomeConquista("Comunicador");
        gamificacao3.setImagemConquista(R.drawable.ic_communicate);
        gamificacao3.setImagemDesbloquear(R.drawable.ic_lock);
        gamificacoes.add(gamificacao3);

        Gamificacao gamificacao4 = new Gamificacao();
        gamificacao4.setNomeConquista("Conhecendo o mercado");
        gamificacao4.setImagemConquista(R.drawable.ic_knowingthemarket);
        gamificacao4.setImagemDesbloquear(R.drawable.ic_block);
        gamificacoes.add(gamificacao4);

        Gamificacao gamificacao5 = new Gamificacao();
        gamificacao5.setNomeConquista("Compra consciente");
        gamificacao5.setImagemConquista(R.drawable.ic_buy);
        gamificacao5.setImagemDesbloquear(R.drawable.ic_block);
        gamificacoes.add(gamificacao5);

        Gamificacao gamificacao6 = new Gamificacao();
        gamificacao6.setNomeConquista("Campeão");
        gamificacao6.setImagemConquista(R.drawable.ic_champion);
        gamificacao6.setImagemDesbloquear(R.drawable.ic_block);
        gamificacoes.add(gamificacao6);

        textViewTituloUsuarioGamificacao.setText("Comunicador");
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
                if (dataSnapshot.getValue() != null){
                    usuario = dataSnapshot.getValue(Usuario.class);

                    String dataNascimento = usuario.getDiaNascimento() + "/" +
                            switchMes(usuario.getMesNascimento()) + "/" +
                            usuario.getAnoNascimento();

                    textViewNome.setText(usuario.getNome());
                    textViewDataNascimento.setText(dataNascimento);

                    if (usuario.isAdmin()){
                        buttonAdicionarEmpresa.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), "Perfil não encontrado. Por favor, cadastre seu perfil.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    startActivity(new Intent(getContext(), CadastroActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String switchMes(String mes) {
        String numeroMes = "";
        switch (mes.toLowerCase()){
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

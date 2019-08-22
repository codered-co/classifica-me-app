package com.example.classificame.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class EditarPerfilActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonEdit;
    private EditText editTextNome, editTextCidade,
            editTextEstado, editTextTelefone;
    private Spinner spinnerPaises;
    private ImageView imageViewPerfilEdit;

    private Usuario usuario;

    private Locale[] locales = Locale.getAvailableLocales();
    private ArrayList<String> paises = new ArrayList<>();
    private ArrayAdapter<String> paisesAdapter;

    private FirebaseAuth auth;
    private DatabaseReference firebase;
    private ValueEventListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        auth = ConfigFirebase.getAuth();
        firebase = ConfigFirebase.getDatabase();

        getSupportActionBar().setTitle("Editar Perfil");

        editTextNome = findViewById(R.id.editText_nome_edit_perfil);
        editTextCidade = findViewById(R.id.editText_cidade_edit_perfil);
        editTextEstado = findViewById(R.id.editText_estado);
        editTextTelefone = findViewById(R.id.editText_telefone_edit_perfil);
        floatingActionButtonEdit = findViewById(R.id.floatingActionButton_salvar_edit_perfil);
        imageViewPerfilEdit = findViewById(R.id.imageView_user_perfil);
        spinnerPaises = findViewById(R.id.spinner_paises);

        paisesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paises);
        configurarSpinnerPaises();

        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAlteracao();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarPerfil();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebase.removeEventListener(listener);
        }
    }

    private void recuperarPerfil() {
        String idUsuario = Base64Helper.codificarBase64(auth.getCurrentUser().getEmail());
        listener = firebase.child("usuario").child(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(Usuario.class);

                editTextNome.setText(usuario.getNome());
                editTextCidade.setText(usuario.getCidade());
                editTextEstado.setText(usuario.getEstado());
                editTextTelefone.setText(usuario.getTelefone());
                spinnerPaises.setSelection(paisesAdapter.getPosition(usuario.getPais()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void salvarAlteracao(){
        String idUsuario = Base64Helper.codificarBase64(auth.getCurrentUser().getEmail());
        DatabaseReference usuarioRef = firebase.child("usuario").child(idUsuario);
        String nome = editTextNome.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String estado = editTextEstado.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String pais = spinnerPaises.getSelectedItem().toString();

        usuarioRef.child("nome").setValue(nome);
        usuarioRef.child("estado").setValue(estado.toUpperCase());
        usuarioRef.child("cidade").setValue(cidade);
        usuarioRef.child("telefone").setValue(telefone);
        usuarioRef.child("pais").setValue(pais);
    }

    private void configurarSpinnerPaises() {
        for (Locale locale : locales) {
            String pais = locale.getDisplayCountry();
            if (pais.trim().length() > 0 && !paises.contains(pais)) {
                paises.add(pais);
            }
        }

        Collections.sort(paises);

        paisesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPaises.setAdapter(paisesAdapter);
        spinnerPaises.setSelection(paisesAdapter.getPosition("Brasil"));
    }
}

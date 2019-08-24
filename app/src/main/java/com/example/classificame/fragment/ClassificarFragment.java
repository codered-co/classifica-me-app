package com.example.classificame.fragment;

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

import com.example.classificame.R;
import com.example.classificame.adapter.AdapterClassificar;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.model.Empresa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificarFragment extends Fragment {

    private DatabaseReference firebase;
    private ValueEventListener listener;

    private RecyclerView recyclerViewEmpresa;
    private ArrayList<Empresa> empresas = new ArrayList<>();
    private Empresa empresa;
    private AdapterClassificar adapterClassificar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classificar, container, false);

        firebase = ConfigFirebase.getDatabase();

        recyclerViewEmpresa = view.findViewById(R.id.recyclerViewClassificar);
        recyclerViewEmpresa.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterClassificar = new AdapterClassificar(empresas, getContext());
        recyclerViewEmpresa.setAdapter(adapterClassificar);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item1 = menu.findItem(R.id.action_editar);
        MenuItem item2 = menu.findItem(R.id.action_logout);
        MenuItem item3 = menu.findItem(R.id.action_check);
        if(item1 != null)
            item1.setVisible(false);
        if (item2 != null)
            item2.setVisible(false);
        if (item3 != null) {
            item3.setVisible(false);
        }

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ordenar) {
            Collections.sort(empresas, adapterClassificar.sortNameEmpresa);
            adapterClassificar.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarEmpresas();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (listener != null){
            firebase.removeEventListener(listener);
        }
    }

    private void recuperarEmpresas() {
        listener = firebase.child("empresa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                empresas.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    empresa = dados.getValue(Empresa.class);
                    empresas.add(empresa);
                }
                adapterClassificar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


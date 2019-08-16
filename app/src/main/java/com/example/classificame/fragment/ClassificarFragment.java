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

import com.example.classificame.R;
import com.example.classificame.activity.EditarPerfilActivity;
import com.example.classificame.adapter.AdapterClassificar;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificarFragment extends Fragment {
    private RecyclerView recyclerViewEmpresa;

    private ArrayList<Empresa> empresas = new ArrayList<>();
    private AdapterClassificar adapterClassificar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classificar, container, false);

        //Recycler
        recyclerViewEmpresa = view.findViewById(R.id.recyclerViewClassificar);
        recyclerViewEmpresa.setLayoutManager(new LinearLayoutManager(getContext()));
        //Adapter
        adapterClassificar = new AdapterClassificar(empresas, getContext());
        recyclerViewEmpresa.setAdapter(adapterClassificar);
        criarEmpresa();
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

        if(item1 != null)
            item1.setVisible(false);
        if (item2 != null)
            item2.setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ordenar) {
            //Ordenar lista em ordem alfabética
        }
        return super.onOptionsItemSelected(item);
    }

    private void criarEmpresa() {
        Empresa empresa1 = new Empresa();
        empresa1.setNomeEmpresa("CodeRed");
        empresa1.setCategoriaEmpresa("Tecnologia");
        empresa1.setDescricaoEmpresa("Programar se aprende aqui");
        empresa1.setLocalEmpresa("Itaperuna-RJ");
        empresa1.setTipoEmpresa("Privada/Pública");
        empresas.add(empresa1);
    }
    private void recuperarEmpresa() {
        //Firebase
    }
}


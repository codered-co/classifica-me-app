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
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.adapter.AdapterClassificar;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Recycler
        recyclerViewEmpresa = getView().findViewById(R.id.recyclerViewClassificar);
        recyclerViewEmpresa.setLayoutManager(new LinearLayoutManager(getContext()));
        //Adapter
        adapterClassificar = new AdapterClassificar(empresas, getContext());
        recyclerViewEmpresa.setAdapter(adapterClassificar);

        criarEmpresa();
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

    private void criarEmpresa() {
        /*Empresa empresa1 = new Empresa();
        empresa1.setNomeEmpresa("CodeRed");
        empresa1.setCategoriaEmpresa("Tecnologia");
        empresa1.setDescricaoEmpresa("Programar se aprende aqui");
        empresa1.setLocalEmpresa("Itaperuna-RJ");
        empresa1.setTipoEmpresa("Privada/Pública");
        empresas.add(empresa1);

        Empresa empresa2 = new Empresa();
        empresa2.setNomeEmpresa("Braseiro");
        empresa2.setCategoriaEmpresa("Tecnologia");
        empresa2.setDescricaoEmpresa("Programar se aprende aqui");
        empresa2.setLocalEmpresa("Itaperuna-RJ");
        empresa2.setTipoEmpresa("Privada/Pública");
        empresas.add(empresa2);

        Empresa empresa3 = new Empresa();
        empresa3.setNomeEmpresa("Ampla");
        empresa3.setCategoriaEmpresa("Tecnologia");
        empresa3.setDescricaoEmpresa("Programar se aprende aqui");
        empresa3.setLocalEmpresa("Itaperuna-RJ");
        empresa3.setTipoEmpresa("Privada/Pública");
        empresas.add(empresa3);*/
    }

    private void recuperarEmpresa() {
        //Firebase
    }
}


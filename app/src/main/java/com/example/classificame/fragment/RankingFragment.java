package com.example.classificame.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classificame.R;
import com.example.classificame.adapter.AdaterRanking;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    private AdaterRanking adaterRanking;
    private ArrayList<Empresa> empresaArrayList = new ArrayList<>();

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerViewRankingEmpresas = getView().findViewById(R.id.recycleView_ranking_empresas);
        recyclerViewRankingEmpresas.setLayoutManager(new LinearLayoutManager(getContext()));


        Empresa empresa1 = new Empresa();
        empresa1.setNomeEmpresa("Code Red");
        empresa1.setDescricaoEmpresa("Empresa de Teste");
        empresa1.setLocalEmpresa("Itaperuna - RJ");
        empresa1.setCategoriaEmpresa("Teste");
        empresa1.setClassificacaoEmpresa((float) 3.9);


        Empresa empresa2 = new Empresa();
        empresa2.setNomeEmpresa("Odin Son");
        empresa2.setDescricaoEmpresa("Empresa de controle de tempestades");
        empresa2.setLocalEmpresa("Yggdrazil");
        empresa2.setCategoriaEmpresa("Controle");
        empresa2.setClassificacaoEmpresa((float) 5.0);


        empresaArrayList.add(empresa1);
        empresaArrayList.add(empresa2);

        adaterRanking = new AdaterRanking(empresaArrayList, getContext());
        recyclerViewRankingEmpresas.setAdapter(adaterRanking);

    }

    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView bottomNavigationView = getView().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}

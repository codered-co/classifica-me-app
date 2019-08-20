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
import com.example.classificame.adapter.AdapterRanking;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    private AdapterRanking adapterRanking;
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



        adapterRanking = new AdapterRanking(empresaArrayList, getContext());
        recyclerViewRankingEmpresas.setAdapter(adapterRanking);

    }

    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}

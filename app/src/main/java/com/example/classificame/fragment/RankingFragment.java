package com.example.classificame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classificame.R;
import com.example.classificame.adapter.AdapterRanking;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.model.Empresa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    private DatabaseReference firebase;
    private ValueEventListener listener;

    private AdapterRanking adapterRanking;
    private ArrayList<Empresa> empresas = new ArrayList<>();
    private Empresa empresa;

    public RankingFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        firebase = ConfigFirebase.getDatabase();

        RecyclerView recyclerViewEmpresas = view.findViewById(R.id.recycleView_ranking_empresas);
        recyclerViewEmpresas.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterRanking = new AdapterRanking(empresas, getContext());
        recyclerViewEmpresas.setAdapter(adapterRanking);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.VISIBLE);
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

    private void recuperarEmpresas(){
        listener = firebase.child("empresa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                empresas.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    empresa = dados.getValue(Empresa.class);
                    empresas.add(empresa);
                }
                adapterRanking.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

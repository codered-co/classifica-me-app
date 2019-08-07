package com.example.classificame.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.classificame.R;
import com.example.classificame.activity.SugerirEmpresaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificarFragment extends Fragment {

    private Button buttonSugerir;

    public ClassificarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classificar, container, false);

        buttonSugerir = view.findViewById(R.id.button_sugerir_empresa);

        buttonSugerir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           // Intent i = new Intent(getContext(), SugerirEmpresaActivity.class);
           // startActivity(i);

            }
        });

        return view;
    }


}

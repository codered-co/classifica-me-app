package com.example.classificame.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.classificame.R;
import com.example.classificame.activity.MainActivity;
import com.example.classificame.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private Button buttonLogout;

    public PerfilFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        buttonLogout = view.findViewById(R.id.button_logout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = ConfigFirebase.getAuth();
                auth.signOut();
                getActivity().finish();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        return view;
    }

}

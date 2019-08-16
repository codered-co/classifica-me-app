package com.example.classificame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;

public class DescricaoEmpresaFragment extends Fragment {

    private TextView textViewNomeEmpresa, textViewDescricaoEmpresa, textViewTipoEmpresa, textViewCategoriaEmpresa, textViewTelefoneEmpresa, textViewCidadeEmpresa, textViewEnderecoEmpresa, textViewCnpjEmpresa;
    private ImageView imageViewPerfilEmpresa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descricao_empresa, container, false);

        textViewNomeEmpresa = view.findViewById(R.id.textView_nome_descricao_empresa);
        textViewDescricaoEmpresa = view.findViewById(R.id.textView_desc_descricao_empresa);
        textViewTipoEmpresa = view.findViewById(R.id.textView_tipo_descricao_empresa);
        textViewCategoriaEmpresa = view.findViewById(R.id.textView_categoria_descricao_empresa);
        textViewTelefoneEmpresa = view.findViewById(R.id.textView_telefone_descricao_empresa);
        textViewCidadeEmpresa = view.findViewById(R.id.textView_cidade_descricao_empresa);
        textViewEnderecoEmpresa = view.findViewById(R.id.textView_endereco_descricao_empresa);
        textViewCnpjEmpresa = view.findViewById(R.id.textView_cnpj_descricao_empresa);
        imageViewPerfilEmpresa = view.findViewById(R.id.imageView_perfil_descricao_empresa);
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void recuperarDados() {
        //Firebase
    }
}

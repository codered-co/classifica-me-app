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

    private TextView textViewNome, textViewDescricao, textViewTipo,
            textViewCategoria, textViewTelefone, textViewEndereco,
            textViewCnpj;
    private ImageView imageViewPerfil;
    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_descricao_empresa, container, false);

        textViewNome = view.findViewById(R.id.textView_nome_descricao_empresa);
        textViewDescricao = view.findViewById(R.id.textView_desc_descricao_empresa);
        textViewTipo = view.findViewById(R.id.textView_tipo_descricao_empresa);
        textViewCategoria = view.findViewById(R.id.textView_categoria_descricao_empresa);
        textViewTelefone = view.findViewById(R.id.textView_telefone_descricao_empresa);
        textViewEndereco = view.findViewById(R.id.textView_endereco_descricao_empresa);
        textViewCnpj = view.findViewById(R.id.textView_cnpj_descricao_empresa);
        imageViewPerfil = view.findViewById(R.id.imageView_perfil_descricao_empresa);

        bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.GONE);

        Bundle bundle = this.getArguments();

        textViewNome.setText("Nome: " + bundle.getString("NomeEmpresa"));
        textViewDescricao.setText("Descricao: " + bundle.getString("DescricaoEmpresa"));
        textViewTipo.setText("Tipo: " + bundle.getString("TipoEmpresa"));
        textViewCategoria.setText("Categoria: " + bundle.getString("CategoriaEmpresa"));
        textViewTelefone.setText("Telefone: " + bundle.getString("TelefoneEmpresa"));
        textViewCnpj.setText("CNPJ: " + bundle.getString("CnpjEmpresa"));
        textViewEndereco.setText("Endereco: " + bundle.getString("EnderecoEmpresa"));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

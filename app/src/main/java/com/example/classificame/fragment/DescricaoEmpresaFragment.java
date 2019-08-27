package com.example.classificame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;

public class DescricaoEmpresaFragment extends Fragment {

    private EditText editTextTipo,
            editTextTelefone, editTextEndereco,
            editTextCnpj;
    private TextView textViewNome, textViewCategoria, textViewDescricao;
    private ImageView imageViewPerfil;
    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_descricao_empresa, container, false);

        findView(view);

        bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.GONE);

        setTexts();

        return view;
    }

    private void setTexts() {
        Bundle bundle = getArguments();
        textViewNome.setText(bundle.getString("NomeEmpresa"));
        textViewDescricao.setText(bundle.getString("DescricaoEmpresa"));
        editTextTipo.setText(bundle.getString("TipoEmpresa"));
        textViewCategoria.setText(bundle.getString("CategoriaEmpresa"));
        editTextTelefone.setText(bundle.getString("TelefoneEmpresa"));
        editTextCnpj.setText(bundle.getString("CnpjEmpresa"));
        editTextEndereco.setText(bundle.getString("EnderecoEmpresa"));
    }

    private void findView(View view) {
        textViewNome = view.findViewById(R.id.textView_nome_descricao_empresa);
        textViewDescricao = view.findViewById(R.id.textView_desc_descricao_empresa);
        textViewCategoria = view.findViewById(R.id.textView_categoria_descricao_empresa);
        editTextTipo = view.findViewById(R.id.editText_tipo_descricao_empresa);
        editTextTelefone = view.findViewById(R.id.editText_telefone_descricao_empresa);
        editTextEndereco = view.findViewById(R.id.editText_endereco_descricao_empresa);
        editTextCnpj = view.findViewById(R.id.editText_cnpj_descricao_empresa);
        imageViewPerfil = view.findViewById(R.id.imageView_perfil_descricao_empresa);
    }

}

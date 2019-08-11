package com.example.classificame.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;

public class AdapterClassificar extends RecyclerView.Adapter<AdapterClassificar.ViewHolderEmpresa> {


    private ArrayList<Empresa> empresas;

    public AdapterClassificar(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    @NonNull
    @Override
    public ViewHolderEmpresa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_classificar, viewGroup, false);
        return new ViewHolderEmpresa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmpresa viewHolderEmpresa, int posicao) {
        Empresa empresa = empresas.get(posicao);
        viewHolderEmpresa.nomeEmpresa.setText(empresa.getNomeEmpresa());
        viewHolderEmpresa.descricaoEmpresa.setText(empresa.getDescricaoEmpresa());
        viewHolderEmpresa.localEmpresa.setText(empresa.getLocalEmpresa());
        viewHolderEmpresa.tipoEmpresa.setText(empresa.getTipoEmpresa());
    }


    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public class ViewHolderEmpresa extends RecyclerView.ViewHolder {

        private TextView nomeEmpresa, descricaoEmpresa, localEmpresa, tipoEmpresa;
        private ImageView imagemEmpresa;
        private Button classificarEmpresa;

        public ViewHolderEmpresa(@NonNull View itemView) {
            super(itemView);

            nomeEmpresa = itemView.findViewById(R.id.textView_nome_empresa_adapter);
            descricaoEmpresa = itemView.findViewById(R.id.textView_descricao_empresa_adapter);
            localEmpresa = itemView.findViewById(R.id.textView_local_empresa_adapter);
            classificarEmpresa = itemView.findViewById(R.id.button_classificar_adapter);
            imagemEmpresa = itemView.findViewById(R.id.imageView_empresa_adapter);
            tipoEmpresa = itemView.findViewById(R.id.textView_tipo_empresa_adapter);

        }
    }

}

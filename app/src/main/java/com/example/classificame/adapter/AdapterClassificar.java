package com.example.classificame.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.activity.ClassificandoActivity;
import com.example.classificame.helper.Base64Helper;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;
import java.util.Comparator;

public class AdapterClassificar extends RecyclerView.Adapter<AdapterClassificar.ViewHolderEmpresa> {


    private ArrayList<Empresa> empresas;
    private Context context;

    public AdapterClassificar(ArrayList<Empresa> empresas, Context context) {
        this.empresas = empresas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEmpresa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_classificar, viewGroup, false);

        return new ViewHolderEmpresa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmpresa viewHolderEmpresa, final int posicao) {
        Empresa empresa = empresas.get(posicao);
        String local = empresa.getRua() + ", " +
                empresa.getNumero() + ", " +
                empresa.getBairro() + ", " +
                empresa.getCidade() + " - " +
                empresa.getEstado();

        viewHolderEmpresa.nomeEmpresa.setText(empresa.getNome());
        viewHolderEmpresa.localEmpresa.setText(local);
        viewHolderEmpresa.tipoEmpresa.setText(empresa.getTipo());
        //imagemEmpresa -->

        viewHolderEmpresa.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa empresaSelecionada = empresas.get(posicao);
                Bundle bundle = new Bundle();

                bundle.putString("NomeEmpresa", empresaSelecionada.getNome());
                bundle.putString("DescricaoEmpresa", empresaSelecionada.getDescricao());
                bundle.putString("IdEmpresa", Base64Helper.codificarBase64(empresaSelecionada.getCnpj()));
                bundle.putDouble("VotoAtendimentoCliente", empresaSelecionada.getVoto().getAtendimentoCliente());
                bundle.putDouble("VotoFormaPagamento", empresaSelecionada.getVoto().getFormaPagamento());
                bundle.putDouble("VotoPossibilidadedeVoltar", empresaSelecionada.getVoto().getPossibilidadeVoltar());
                bundle.putDouble("VotoServicoEntrega", empresaSelecionada.getVoto().getServicoEntrega());

                Intent intent = new Intent(context, ClassificandoActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }


    public static Comparator<Empresa> sortNameEmpresa = new Comparator<Empresa>() {
        @Override
        public int compare(Empresa empresa, Empresa t1) {
            return empresa.getNome().compareTo(t1.getNome());
        }
    };

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public class ViewHolderEmpresa extends RecyclerView.ViewHolder {

        private TextView nomeEmpresa, localEmpresa, tipoEmpresa;
        private ImageView imagemEmpresa;

        public ViewHolderEmpresa(@NonNull View itemView) {
            super(itemView);

            nomeEmpresa = itemView.findViewById(R.id.textView_nome_empresa_adapter);
            localEmpresa = itemView.findViewById(R.id.textView_local_empresa_adapter);
            imagemEmpresa = itemView.findViewById(R.id.imageView_empresa_adapter);
            tipoEmpresa = itemView.findViewById(R.id.textView_tipo_empresa_adapter);
        }
    }

}

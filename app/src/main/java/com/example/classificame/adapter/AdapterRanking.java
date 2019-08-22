package com.example.classificame.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.fragment.DescricaoEmpresaFragment;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.AdapterRankingViewHolder> {

    private ArrayList<Empresa> empresaArrayList;
    private Context context;

    public AdapterRanking(ArrayList<Empresa> empresaArrayList, Context context) {
        this.empresaArrayList = empresaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRankingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_ranking, viewGroup, false);

        return new AdapterRankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRankingViewHolder adapterRankingViewHolder, final int posicao) {
        Empresa empresa = empresaArrayList.get(posicao);
        String local = empresa.getRua() + ", " +
                empresa.getNumero() + ", " +
                empresa.getBairro() + ", " +
                empresa.getCidade() + " - " +
                empresa.getEstado();

        adapterRankingViewHolder.textViewNomeEmpresa.setText(empresa.getNome());
        adapterRankingViewHolder.textViewDescricaoEmpresa.setText(empresa.getDescricao());
        adapterRankingViewHolder.textViewLocalEmpresa.setText(local);
        adapterRankingViewHolder.textViewCategoriaEmpresa.setText(empresa.getCategoria());

        String teste = String.valueOf(empresa.getClassificacao());
        adapterRankingViewHolder.textViewClassificaEmpresa.setText(teste);

        double classificacao = empresa.getClassificacao();

        if ( classificacao >= 5 ){
            adapterRankingViewHolder.imageViewClassificacaoEmpresa.setImageResource(R.drawable.srv_ic_rating_amazing);
        } else if (classificacao >= 4 && classificacao < 5){
            adapterRankingViewHolder.imageViewClassificacaoEmpresa.setImageResource(R.drawable.srv_ic_rating_good);
        } else if (classificacao >= 3 && classificacao < 4){
            adapterRankingViewHolder.imageViewClassificacaoEmpresa.setImageResource(R.drawable.srv_ic_rating_meh);
        } else if (classificacao >= 2 && classificacao < 3){
            adapterRankingViewHolder.imageViewClassificacaoEmpresa.setImageResource(R.drawable.srv_ic_rating_awful);
        } else {
            adapterRankingViewHolder.imageViewClassificacaoEmpresa.setImageResource(R.drawable.ic_arrow);
        }

        adapterRankingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa empresa = empresaArrayList.get(posicao);
                String endereco = empresa.getRua() + ", " +
                        empresa.getBairro() + ", Nº " +
                        empresa.getNumero() + ", " +
                        empresa.getCidade() + " - " +
                        empresa.getEstado();
                Fragment fragment = new DescricaoEmpresaFragment();

                Bundle bundle = new Bundle();
                bundle.putString("NomeEmpresa", empresa.getNome());
                bundle.putString("DescricaoEmpresa", empresa.getDescricao());
                bundle.putString("TipoEmpresa", empresa.getTipo());
                bundle.putString("CategoriaEmpresa", empresa.getCategoria());
                bundle.putString("TelefoneEmpresa", empresa.getTelefone());
                bundle.putString("CnpjEmpresa", empresa.getCnpj());
                bundle.putString("EnderecoEmpresa", endereco);

                fragment.setArguments(bundle);

                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_container, fragment, "descricao")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return empresaArrayList.size();
    }

    public class AdapterRankingViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomeEmpresa;
        TextView textViewDescricaoEmpresa;
        TextView textViewLocalEmpresa;
        TextView textViewCategoriaEmpresa;
        TextView textViewClassificaEmpresa;
        ImageView imageViewClassificacaoEmpresa;
        ImageView imageViewLogoEmpresa;


        public AdapterRankingViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNomeEmpresa = itemView.findViewById(R.id.textView_nome_empresa_adapter_ranking);
            textViewDescricaoEmpresa = itemView.findViewById(R.id.textView_descricao_empresa_adapter_ranking);
            textViewLocalEmpresa = itemView.findViewById(R.id.textView_local_empresa_adapter_ranking);
            textViewCategoriaEmpresa = itemView.findViewById(R.id.textView_tipo_empresa_adapter_ranking);
            textViewClassificaEmpresa = itemView.findViewById(R.id.textView_classificacao_empresa_adapter_ranking);
            imageViewClassificacaoEmpresa = itemView.findViewById(R.id.imageView_classificacao_empresa_adapter_ranking);
            imageViewLogoEmpresa = itemView.findViewById(R.id.imageView_empresa_adapter_ranking);
        }
    }



}

package com.example.classificame.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.model.Gamificacao;

import java.util.ArrayList;

public class AdapterGamificacao extends RecyclerView.Adapter<AdapterGamificacao.ViewHolderGamificacao> {

    private ArrayList<Gamificacao> gamificacoes;
    private Context context;

    public AdapterGamificacao(ArrayList<Gamificacao> gamificacoes, Context context) {
        this.gamificacoes = gamificacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderGamificacao onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_gamificacao, viewGroup, false);
        return new ViewHolderGamificacao(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGamificacao viewHolderGamificacao, int posicao) {
        Gamificacao gamificacao = gamificacoes.get(posicao);
        viewHolderGamificacao.nomeAcaoDesbloqueada.setText(gamificacao.getNomeAcao());
    }

    @Override
    public int getItemCount() {
        return gamificacoes.size();
    }

    public class ViewHolderGamificacao extends RecyclerView.ViewHolder {

        private ImageView imagemBloqueado;
        private ImageView imagemEmblemaDesbloqueado;
        private TextView nomeAcaoDesbloqueada;

        public ViewHolderGamificacao(@NonNull View itemView) {
            super(itemView);

            imagemBloqueado = itemView.findViewById(R.id.imageView_icon_block_gamificacao);
            imagemEmblemaDesbloqueado = itemView.findViewById(R.id.imageView_emblema_gamificacao);
            nomeAcaoDesbloqueada = itemView.findViewById(R.id.textView_nome_gamificacao_desbloqueada);
        }
    }

}
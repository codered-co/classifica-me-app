package com.example.classificame.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classificame.R;
import com.example.classificame.model.Conquista;

import java.util.ArrayList;

public class AdapterConquista extends RecyclerView.Adapter<AdapterConquista.ViewHolderConquista> {

    private ArrayList<Conquista> conquistas;
    private Context context;

    public AdapterConquista(ArrayList<Conquista> conquistas, Context context) {
        this.conquistas = conquistas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderConquista onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_conquista, viewGroup, false);
        return new ViewHolderConquista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConquista viewHolderGamificacao, int posicao) {
        Conquista conquista = conquistas.get(posicao);
        viewHolderGamificacao.nomeConquista.setText(conquista.getNomeConquista());
        viewHolderGamificacao.imagemConquista.setImageResource(conquista.getImagemEmblemaConquista());
        viewHolderGamificacao.imagemIconBlock.setImageResource(conquista.getImagemDesbloquearConquista());
        
        viewHolderGamificacao.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Conquista em breve", Toast.LENGTH_SHORT).show();
            }
        });


        //Implementar click no item da listView
    }

    @Override
    public int getItemCount() {
        return conquistas.size();
    }

    public class ViewHolderConquista extends RecyclerView.ViewHolder {

        private ImageView imagemIconBlock;
        private ImageView imagemConquista;
        private TextView nomeConquista;

        public ViewHolderConquista(@NonNull View itemView) {
            super(itemView);

            imagemIconBlock = itemView.findViewById(R.id.imageView_icon_block_conquista);
            imagemConquista = itemView.findViewById(R.id.imageView_emblema_conquista);
            nomeConquista = itemView.findViewById(R.id.textView_nome_conquista_desbloqueada);
        }
    }

}
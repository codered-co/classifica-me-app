package com.example.classificame.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classificame.R;
import com.example.classificame.adapter.AdapterClassificar;
import com.example.classificame.model.Empresa;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificarFragment extends Fragment {
    private RecyclerView recyclerViewEmpresa;

    private ArrayList<Empresa> empresas = new ArrayList<>();
    private AdapterClassificar adapterClassificar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classificar, container, false);

        //Recycler
        recyclerViewEmpresa = view.findViewById(R.id.recyclerViewClassificar);
        recyclerViewEmpresa.setLayoutManager(new LinearLayoutManager(getContext()));
        //Adapter
        adapterClassificar = new AdapterClassificar(empresas, getContext());
        recyclerViewEmpresa.setAdapter(adapterClassificar);
        criarEmpresa();
        return view;
    }

    private void criarEmpresa() {
        Empresa empresa1 = new Empresa();
        empresa1.setNomeEmpresa("CodeRed");
        empresa1.setCategoriaEmpresa("Tecnologia");
        empresa1.setDescricaoEmpresa("Programar se aprende aqui");
        empresa1.setLocalEmpresa("Itaperuna-RJ");
        empresa1.setTipoEmpresa("Privada/Pública");
        empresas.add(empresa1);
    }
    private void recuperarEmpresa() {
        //Firebase
    }
}



/* CÓDIGO ESTAVA SOBRE POSTO


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ratingViewAtendimentoCliente = getView().findViewById(R.id.simpleRatingView_atendimento_cliente);
        ratingViewFormaPagamento = getView().findViewById(R.id.simpleRatingView_forma_pagamento);
        ratingViewServicoEntrega = getView().findViewById(R.id.simpleRatingView_servico_entrega);
        ratingViewPossibilidadeVoltar = getView().findViewById(R.id.simpleRatingView_possibilidade_voltar);

        textViewValorAtendimentoCliente = getView().findViewById(R.id.textView_ratingValue_atendimento_cliente);
        textViewValorFormaPagamento = getView().findViewById(R.id.textView_ratingValue_forma_pagamento);
        textViewValorServicoEntrega = getView().findViewById(R.id.textView_ratingValue_servico_entrega);
        textViewValorPossibilidadeVoltar = getView().findViewById(R.id.textView_ratingValue_possibilidade_voltar);


        textViewNomeEmpresa = getView().findViewById(R.id.textView_nome_empresa_classificar);
        textViewDescricaoEmpresa = getView().findViewById(R.id.textView_descricao_empresa_classificar);



        addListenerOnRatingBar(ratingViewAtendimentoCliente, textViewValorAtendimentoCliente);
        addListenerOnRatingBar(ratingViewFormaPagamento, textViewValorFormaPagamento);
        addListenerOnRatingBar(ratingViewServicoEntrega, textViewValorServicoEntrega);
        addListenerOnRatingBar(ratingViewPossibilidadeVoltar, textViewValorPossibilidadeVoltar);

        addListenerOnButtonEnviar();
    }

    public void addListenerOnRatingBar(SimpleRatingView simpleRatingView, final TextView txtRatingValue) {
        //se o valor do ratingBar muda,
        //exibe automaticamente o resultado atual
        simpleRatingView.setListener(new SimpleRatingView.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(SimpleRatingView.Rating rating) {
                txtRatingValue.setText(getString(rating.getStringRes()));
            }
        });
    }

    public int pegarValor(TextView textView){
        int valorRankin = 0;
        String ranking = textView.getText().toString();
        if (ranking.contentEquals("Bronze")){
            valorRankin = 2;
        } else if (ranking.contentEquals("Prata")){
            valorRankin = 3;
        } else if (ranking.contentEquals("Ouro")){
            valorRankin = 4;
        } else if (ranking.contentEquals("Diamante")){
            valorRankin = 5;
        } else{
            valorRankin = 0;
        }
        return valorRankin;
    }


    public void addListenerOnButtonEnviar() {
        textViewValorAtendimentoCliente = getView().findViewById(R.id.textView_ratingValue_atendimento_cliente);
        textViewValorFormaPagamento = getView().findViewById(R.id.textView_ratingValue_forma_pagamento);
        textViewValorServicoEntrega = getView().findViewById(R.id.textView_ratingValue_servico_entrega);
        textViewValorPossibilidadeVoltar= getView().findViewById(R.id.textView_ratingValue_possibilidade_voltar);


        Button buttonSubmit = getView().findViewById(R.id.button_submit_valor);
        //se clickar recupera os valores votados em cada categoria
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rankingAtendimentoCliente = pegarValor(textViewValorAtendimentoCliente);
                float rankingFormaPagamento = pegarValor(textViewValorFormaPagamento);
                float rankingServicoEntrega = pegarValor(textViewValorServicoEntrega);
                float rankingPossibilidadeVoltar = pegarValor(textViewValorPossibilidadeVoltar);


            }
        });
*/


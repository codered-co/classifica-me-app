package com.example.classificame.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.classificame.R;

import app.youkai.simpleratingview.SimpleRatingView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificarFragment extends Fragment {

    private SimpleRatingView ratingViewAtendimentoCliente;
    private SimpleRatingView ratingViewFormaPagamento;
    private SimpleRatingView ratingViewServicoEntrega;
    private SimpleRatingView ratingViewPossibilidadeVoltar;

    private TextView textViewValorAtendimentoCliente;
    private TextView textViewValorFormaPagamento;
    private TextView textViewValorServicoEntrega;
    private TextView textViewValorPossibilidadeVoltar;


    private TextView textViewNomeEmpresa;
    private TextView textViewDescricaoEmpresa;

    private Button buttonSugerir;

    public ClassificarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classificar, container, false);

        buttonSugerir = view.findViewById(R.id.button_sugerir_empresa);

        buttonSugerir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), SugerirEmpresaActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

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
        //if rating value is changed,
        //display the current rating value in the result automatically
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
        //if click, then display the current rating value.
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rankingAtendimentoCliente = pegarValor(textViewValorAtendimentoCliente);
                float rankingFormaPagamento = pegarValor(textViewValorFormaPagamento);
                float rankingServicoEntrega = pegarValor(textViewValorServicoEntrega);
                float rankingPossibilidadeVoltar = pegarValor(textViewValorPossibilidadeVoltar);


            }
        });
    }
}

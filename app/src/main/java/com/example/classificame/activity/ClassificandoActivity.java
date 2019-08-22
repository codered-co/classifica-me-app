package com.example.classificame.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.config.ConfigFirebase;
import com.example.classificame.model.Empresa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import app.youkai.simpleratingview.SimpleRatingView;

public class ClassificandoActivity extends AppCompatActivity {

    private SimpleRatingView ratingViewAtendimentoCliente;
    private SimpleRatingView ratingViewFormaPagamento;
    private SimpleRatingView ratingViewServicoEntrega;
    private SimpleRatingView ratingViewPossibilidadeVoltar;

    private TextView textViewValorAtendimentoCliente;
    private TextView textViewValorFormaPagamento;
    private TextView textViewValorServicoEntrega;
    private TextView textViewValorPossibilidadeVoltar;

    private Empresa empresa;

    private DatabaseReference firebase;
    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificar);

        firebase = ConfigFirebase.getDatabase();

        ratingViewAtendimentoCliente = findViewById(R.id.simpleRatingView_atendimento_cliente);
        ratingViewFormaPagamento = findViewById(R.id.simpleRatingView_forma_pagamento);
        ratingViewServicoEntrega = findViewById(R.id.simpleRatingView_qualidade_produto);
        ratingViewPossibilidadeVoltar = findViewById(R.id.simpleRatingView_possibilidade_voltar);

        textViewValorAtendimentoCliente = findViewById(R.id.textView_ratingValue_atendimento_cliente);
        textViewValorFormaPagamento = findViewById(R.id.textView_ratingValue_forma_pagamento);
        textViewValorServicoEntrega = findViewById(R.id.textView_ratingValue_qualidade_produto);
        textViewValorPossibilidadeVoltar = findViewById(R.id.textView_ratingValue_possibilidade_voltar);

        TextView textViewNomeEmpresa = findViewById(R.id.textView_nome_empresa_classificar);
        TextView textViewDescricaoEmpresa = findViewById(R.id.textView_descricao_empresa_classificar);

        addListenerOnRatingBar(ratingViewAtendimentoCliente, textViewValorAtendimentoCliente);
        addListenerOnRatingBar(ratingViewFormaPagamento, textViewValorFormaPagamento);
        addListenerOnRatingBar(ratingViewServicoEntrega, textViewValorServicoEntrega);
        addListenerOnRatingBar(ratingViewPossibilidadeVoltar, textViewValorPossibilidadeVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        textViewNomeEmpresa.setText( bundle.getString("NomeEmpresa"));
        textViewDescricaoEmpresa.setText(bundle.getString("DescricaoEmpresa"));

        addListenerOnButtonEnviar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarEmpresa();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null){
            firebase.removeEventListener(listener);
        }
    }

    public void recuperarEmpresa(){

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

    public int pegarValor(TextView textView) {
        int valorRankin;
        String ranking = textView.getText().toString();
        if (ranking == getString(R.string.srv_awful)) {
            valorRankin = 2;
        } else if (ranking == getString(R.string.srv_meh)) {
            valorRankin = 3;
        } else if (ranking == getString(R.string.srv_good)) {
            valorRankin = 4;
        } else if (ranking == getString(R.string.srv_amazing)) {
            valorRankin = 5;
        } else {
            valorRankin = 0;
        }
        return valorRankin;
    }

    public void addListenerOnButtonEnviar() {
        textViewValorAtendimentoCliente = findViewById(R.id.textView_ratingValue_atendimento_cliente);
        textViewValorFormaPagamento = findViewById(R.id.textView_ratingValue_forma_pagamento);
        textViewValorServicoEntrega = findViewById(R.id.textView_ratingValue_qualidade_produto);
        textViewValorPossibilidadeVoltar = findViewById(R.id.textView_ratingValue_possibilidade_voltar);

        Button buttonSubmit = findViewById(R.id.button_submit_valor);
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

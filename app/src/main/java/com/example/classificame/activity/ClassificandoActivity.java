package com.example.classificame.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.classificame.R;
import com.example.classificame.model.Voto;

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

    private Bundle bundle;
    private String idEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificar);

        getSupportActionBar().setTitle("Classificar");

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
        bundle = intent.getExtras();

        textViewNomeEmpresa.setText( bundle.getString("NomeEmpresa"));
        textViewDescricaoEmpresa.setText(bundle.getString("DescricaoEmpresa"));

        addListenerOnButtonEnviar();
    }

    public void salvarVoto(Voto voto) {
        idEmpresa = bundle.getString("IdEmpresa");
        voto.setIdEmpresaAvaliada(idEmpresa);
        voto.salvarVoto();
        finish();
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
        int valorRanking;
        String ranking = textView.getText().toString();

        switch (ranking){
            case "Bronze":
                valorRanking = 2;
                break;
            case "Prata":
                valorRanking = 3;
                break;
            case "Ouro":
                valorRanking = 4;
                break;
            case "Diamante":
                valorRanking = 5;
                break;
            default:
                valorRanking = 0;
        }

        return valorRanking;
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
                double votoAtendimentoCliente = pegarValor(textViewValorAtendimentoCliente);
                double votoFormaPagamento = pegarValor(textViewValorFormaPagamento);
                double votoServicoEntrega = pegarValor(textViewValorServicoEntrega);
                double votoPossibilidadeVoltar = pegarValor(textViewValorPossibilidadeVoltar);

                Voto votoRecuperado = new Voto();
                votoRecuperado.setAtendimentoCliente(bundle.getDouble("VotoAtendimentoCliente"));
                votoRecuperado.setServicoEntrega(bundle.getDouble("VotoServicoEntrega"));
                votoRecuperado.setPossibilidadeVoltar(bundle.getDouble("VotoPossibilidadedeVoltar"));
                votoRecuperado.setFormaPagamento(bundle.getDouble("VotoFormaPagamento"));

                Voto voto = new Voto();
                voto.setAtendimentoCliente(votoRecuperado.getAtendimentoCliente() + votoAtendimentoCliente);
                voto.setFormaPagamento(votoRecuperado.getFormaPagamento() + votoFormaPagamento);
                voto.setServicoEntrega(votoRecuperado.getServicoEntrega() + votoServicoEntrega);
                voto.setPossibilidadeVoltar(votoRecuperado.getPossibilidadeVoltar() + votoPossibilidadeVoltar);

                salvarVoto(voto);
            }
        });
    }
}

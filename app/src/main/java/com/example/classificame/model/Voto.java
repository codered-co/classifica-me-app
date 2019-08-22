package com.example.classificame.model;

import com.example.classificame.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Voto {
    private double atendimentoCliente, formaPagamento, servicoEntrega,
            possibilidadeVoltar;
    private String idEmpresaAvaliada;

    public void salvarVoto() {
        DatabaseReference firebase = ConfigFirebase.getDatabase();

        firebase.child("empresa")
                .child(this.idEmpresaAvaliada)
                .child("voto")
                .setValue(this);
    }

    public Voto(double atendimentoCliente, double formaPagamento, double servicoEntrega, double possibilidadeVoltar) {
        this.atendimentoCliente = atendimentoCliente;
        this.formaPagamento = formaPagamento;
        this.servicoEntrega = servicoEntrega;
        this.possibilidadeVoltar = possibilidadeVoltar;
    }

    public Voto() {
    }

    @Exclude
    public String getIdEmpresaAvaliada() {
        return idEmpresaAvaliada;
    }

    public void setIdEmpresaAvaliada(String idEmpresaAvaliada) {
        this.idEmpresaAvaliada = idEmpresaAvaliada;
    }

    public double getAtendimentoCliente() {
        return atendimentoCliente;
    }

    public void setAtendimentoCliente(double atendimentoCliente) {
        this.atendimentoCliente = atendimentoCliente;
    }

    public double getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(double formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getServicoEntrega() {
        return servicoEntrega;
    }

    public void setServicoEntrega(double servicoEntrega) {
        this.servicoEntrega = servicoEntrega;
    }

    public double getPossibilidadeVoltar() {
        return possibilidadeVoltar;
    }

    public void setPossibilidadeVoltar(double possibilidadeVoltar) {
        this.possibilidadeVoltar = possibilidadeVoltar;
    }
}

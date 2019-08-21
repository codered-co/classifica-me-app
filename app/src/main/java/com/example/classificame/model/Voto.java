package com.example.classificame.model;

public class Voto {
    private double atendimentoCliente, formaPagamento, servicoEntrega,
            possibilidadeVoltar;

    public Voto(double atendimentoCliente, double formaPagamento, double servicoEntrega, double possibilidadeVoltar) {
        this.atendimentoCliente = atendimentoCliente;
        this.formaPagamento = formaPagamento;
        this.servicoEntrega = servicoEntrega;
        this.possibilidadeVoltar = possibilidadeVoltar;
    }

    public Voto() {
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

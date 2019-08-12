package com.example.classificame.model;

public class Empresa {

    private String nomeEmpresa, descricaoEmpresa, localEmpresa, categoriaEmpresa, imagemEmpresa, tipoEmpresa;
    private float atendimentoCliente, formaPagamento, servicoEntrega, possibilidadeVoltar, classificacaoEmpresa;
    private int votosEmpresa;

    public Empresa() {
        this.nomeEmpresa = nomeEmpresa;
        this.descricaoEmpresa = descricaoEmpresa;
        this.localEmpresa = localEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getDescricaoEmpresa() {
        return descricaoEmpresa;
    }

    public void setDescricaoEmpresa(String descricaoEmpresa) {
        this.descricaoEmpresa = descricaoEmpresa;
    }

    public String getLocalEmpresa() {
        return localEmpresa;
    }

    public void setLocalEmpresa(String localEmpresa) {
        this.localEmpresa = localEmpresa;
    }

    public String getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(String categoriaEmpresa) {
        this.categoriaEmpresa = categoriaEmpresa;
    }

    public String getImagemEmpresa() {
        return imagemEmpresa;
    }

    public void setImagemEmpresa(String imagemEmpresa) {
        this.imagemEmpresa = imagemEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public float getAtendimentoCliente() {
        return atendimentoCliente;
    }

    public void setAtendimentoCliente(float atendimentoCliente) {
        this.atendimentoCliente = atendimentoCliente;
    }

    public float getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(float formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public float getServicoEntrega() {
        return servicoEntrega;
    }

    public void setServicoEntrega(float servicoEntrega) {
        this.servicoEntrega = servicoEntrega;
    }

    public float getPossibilidadeVoltar() {
        return possibilidadeVoltar;
    }

    public void setPossibilidadeVoltar(float possibilidadeVoltar) {
        this.possibilidadeVoltar = possibilidadeVoltar;
    }

    public float getClassificacaoEmpresa() {
        return classificacaoEmpresa;
    }

    public void setClassificacaoEmpresa(float classificacaoEmpresa) {
        this.classificacaoEmpresa = classificacaoEmpresa;
    }

    public int getVotosEmpresa() {
        return votosEmpresa;
    }

    public void setVotosEmpresa(int votosEmpresa) {
        this.votosEmpresa = votosEmpresa;
    }
}

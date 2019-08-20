package com.example.classificame.model;

public class Gamificacao {

    private Usuario usuario;
    private Empresa empresa;
    private Integer qntVotoGamificao;
    private Integer levelGamificacao;
    private String nomeAcao;

    public Gamificacao(Usuario usuario, Empresa empresa, Integer qntVotoGamificao, Integer levelGamificacao, String nomeAcao) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.qntVotoGamificao = qntVotoGamificao;
        this.levelGamificacao = levelGamificacao;
        this.nomeAcao = nomeAcao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getQntVotoGamificao() {
        return qntVotoGamificao;
    }

    public void setQntVotoGamificao(Integer qntVotoGamificao) {
        this.qntVotoGamificao = qntVotoGamificao;
    }

    public Integer getLevelGamificacao() {
        return levelGamificacao;
    }

    public void setLevelGamificacao(Integer levelGamificacao) {
        this.levelGamificacao = levelGamificacao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }
}

package com.example.classificame.model;

public class Gamificacao {

    private Usuario usuario;
    private Empresa empresa;
    private Integer qntVotoGamificacao;
    private String nomeConquista;
    private int imagemEmblemaUsuario;
    private int imagemConquista;
    private int imagemIconBlock;
    private String tituloUsuarioGamificacao;


    public Gamificacao() {

    }

    public String getTituloGamificacao() {
        return tituloUsuarioGamificacao;
    }

    public void setTituloGamificacao(String tituloGamificacao) {
        this.tituloUsuarioGamificacao = tituloGamificacao;
    }

    public Integer getQntVotoGamificacao() {
        return qntVotoGamificacao;
    }

    public void setQntVotoGamificacao(Integer qntVotoGamificacao) {
        this.qntVotoGamificacao = qntVotoGamificacao;
    }

    public int getImagemEmblemaUsuario() {
        return imagemEmblemaUsuario;
    }

    public void setImagemEmblemaUsuario(int imagemEmblemaUsuario) {
        this.imagemEmblemaUsuario = imagemEmblemaUsuario;
    }

    public int getImagemConquista() {
        return imagemConquista;
    }

    public void setImagemConquista(int imagemConquista) {
        this.imagemConquista = imagemConquista;
    }

    public int getImagemDesbloquear() {
        return imagemIconBlock;
    }

    public void setImagemDesbloquear(int imagemDesbloquear) {
        this.imagemIconBlock = imagemDesbloquear;
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
        return qntVotoGamificacao;
    }

    public void setQntVotoGamificao(Integer qntVotoGamificao) {
        this.qntVotoGamificacao = qntVotoGamificao;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }


    private void desbloqueiaConquista() {

    }

    private void alterarTituloUsuario() {

    }
}

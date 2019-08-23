package com.example.classificame.model;

public class Conquista {

    private String nomeConquista;
    private int imagemEmblemaConquista; //Mudar para string
    private int imagemDesbloquearConquista;

    public Conquista() {
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }

    public int getImagemEmblemaConquista() {
        return imagemEmblemaConquista;
    }

    public void setImagemEmblemaConquista(int imagemEmblemaConquista) {
        this.imagemEmblemaConquista = imagemEmblemaConquista;
    }

    public int getImagemDesbloquearConquista() {
        return imagemDesbloquearConquista;
    }

    public void setImagemDesbloquearConquista(int imagemDesbloquearConquista) {
        this.imagemDesbloquearConquista = imagemDesbloquearConquista;
    }
}

package com.example.classificame.model;

import com.example.classificame.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Empresa {


    private String idEmpresa, nomeEmpresa, descricaoEmpresa, cidadeEmpresa,
            estadoEmpresa, ruaEmpresa, bairroEmpresa, numeroEmpresa,
            categoriaEmpresa, imagemEmpresa, tipoEmpresa, cnpjEmpresa;
    private int totalVotos;
    private double classificacaoEmpresa;
    private Voto voto;

    public void salvarEmpresa() {
        DatabaseReference firebase = ConfigFirebase.getDatabase();

        firebase.child("empresa")
                .child(this.idEmpresa)
                .setValue(this);
    }

    public double getClassificacaoEmpresa() {
        return classificacaoEmpresa;
    }

    public void setClassificacaoEmpresa(double classificacaoEmpresa) {
        this.classificacaoEmpresa = classificacaoEmpresa;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    @Exclude
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRuaEmpresa() {
        return ruaEmpresa;
    }

    public void setRuaEmpresa(String ruaEmpresa) {
        this.ruaEmpresa = ruaEmpresa;
    }

    public String getBairroEmpresa() {
        return bairroEmpresa;
    }

    public void setBairroEmpresa(String bairroEmpresa) {
        this.bairroEmpresa = bairroEmpresa;
    }

    public String getNumeroEmpresa() {
        return numeroEmpresa;
    }

    public void setNumeroEmpresa(String numeroEmpresa) {
        this.numeroEmpresa = numeroEmpresa;
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

    public String getCidadeEmpresa() {
        return cidadeEmpresa;
    }

    public void setCidadeEmpresa(String cidadeEmpresa) {
        this.cidadeEmpresa = cidadeEmpresa;
    }

    public String getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(String estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
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

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;

    }
}

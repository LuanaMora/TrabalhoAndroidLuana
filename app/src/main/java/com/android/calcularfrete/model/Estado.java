package com.android.calcularfrete.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Estado implements Serializable {
    private int codigo;
    public  String uf;
    private String descricao;
    private List<Cidade> cidadeList = new ArrayList<Cidade>();
    private Double valorFrete;


    public Estado(int codigo, String uf, String descricao, List<Cidade> cidadeList, Double valorFrete) {
        this.codigo = codigo;
        this.uf = uf;
        this.descricao = descricao;
        this.cidadeList = cidadeList;
        this.valorFrete = valorFrete;
    }

    public Estado() {
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @Override
    public String toString() {
        return  uf + " - " + descricao;
    }

}

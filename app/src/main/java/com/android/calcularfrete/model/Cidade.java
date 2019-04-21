package com.android.calcularfrete.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cidade implements Serializable {
    private int codigo;
    private String descricao;
    private List<Cep> cepList = new ArrayList<Cep>();
    private Estado estado;


    public Cidade() {

    }

    public Cidade(int codigo, String descricao, List<Cep> cepList, Estado estado) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.cepList = cepList;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Cep> getCepList() {
        return cepList;
    }

    public void setCepList(List<Cep> cepList) {
        this.cepList = cepList;
    }


    @Override
    public String toString() {
        return descricao ;
    }
}

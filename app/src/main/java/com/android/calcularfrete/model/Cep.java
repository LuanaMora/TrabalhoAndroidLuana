package com.android.calcularfrete.model;

import java.io.Serializable;

public class Cep implements Serializable {
    private String codigo;
    private Cidade cidade;

    public Cep() {

    }

    public Cep(String codigo, Cidade cidade) {
        this.codigo = codigo;
        this.cidade = cidade;
    }

    public Cep(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
       return codigo;
    }
}

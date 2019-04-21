package com.android.calcularfrete.util;

import com.android.calcularfrete.model.Cep;
import com.android.calcularfrete.model.Cidade;
import com.android.calcularfrete.model.Estado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dados implements Serializable {
    public static List<Estado> estadoList = new ArrayList<Estado>();
    public static List<Cidade> cidadeList = new ArrayList<Cidade>();
    public static List<Cidade> cidadeListAux = new ArrayList<>();
    public static List<Cep> cepList = new ArrayList<Cep>();
    public static List<Cep> cepListAux = new ArrayList<Cep>();
}

package com.android.calcularfrete;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.calcularfrete.model.Cep;
import com.android.calcularfrete.model.Cidade;
import com.android.calcularfrete.model.Estado;
import com.android.calcularfrete.util.Dados;

import java.util.ArrayList;
import java.util.List;

public class LovFrete extends AppCompatActivity {

    private ListView lvFretes;
    private String tipoTela;
    private List<Estado> estadoListAux = new ArrayList<Estado>();
    private Estado estadoAux = new Estado();
    private static Cidade cidadeListAux = new Cidade();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lov_frete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvFretes = findViewById(R.id.lvFretes);
        tipoTela = (String) getIntent().getExtras().get("TIPO");


        if (tipoTela.equalsIgnoreCase("ESTADO")){
            final ArrayAdapter<Estado> adapterEstado = new ArrayAdapter<Estado>(LovFrete.this, R.layout.item_frete, Dados.estadoList);
            lvFretes.setAdapter(adapterEstado);
            lvFretes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (MainActivity.ESTADOORIGEM ) {
                        MainActivity.ESTADOORIGEM = false;
                        Estado estadoSelecionado = adapterEstado.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("ESTADOSELO", estadoSelecionado);
                        setResult(RESULT_OK, output);
                        finish();
                    }

                    if (MainActivity.ESTADODESTINO ){
                        MainActivity.ESTADODESTINO = false;
                        Estado estadoSelecionado = adapterEstado.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("ESTADOSELD", estadoSelecionado);
                        setResult(RESULT_OK, output);
                        finish();
                    }
                }
            });

        } else if (tipoTela.equalsIgnoreCase("CIDADE")){
            final ArrayAdapter<Cidade> adapterCidade = new ArrayAdapter<Cidade>(LovFrete.this, R.layout.item_frete, Dados.cidadeList);
            lvFretes.setAdapter(adapterCidade);
            lvFretes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (MainActivity.CIDADEORIGEM) {
                        MainActivity.CIDADEORIGEM = false;
                        Cidade cidadeSelecionada = adapterCidade.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("CIDADESELO", cidadeSelecionada);
                        setResult(RESULT_OK, output);
                        finish();
                    }
                    if (MainActivity.CIDADEDESTINO){
                        MainActivity.CIDADEDESTINO = false;
                        Cidade cidadeSelecionada = adapterCidade.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("CIDADESELD", cidadeSelecionada);
                        setResult(RESULT_OK, output);
                        finish();
                    }

                }
            });
        } else if (tipoTela.equalsIgnoreCase("CEP")) {
            final ArrayAdapter<Cep> adapterCep = new ArrayAdapter<Cep>(LovFrete.this, R.layout.item_frete, Dados.cepList);
            lvFretes.setAdapter(adapterCep);
            lvFretes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (MainActivity.CEPORIGEM) {
                        MainActivity.CEPORIGEM = false;
                        Cep cepSelecionado = adapterCep.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("CEPSELO", cepSelecionado);
                        setResult(RESULT_OK, output);
                        finish();
                    }
                    if (MainActivity.CEPDESTINO){
                        MainActivity.CEPDESTINO = false;
                        Cep cepSelecionado = adapterCep.getItem(position);
                        Intent output = new Intent();
                        output.putExtra("CEPSELD", cepSelecionado);
                        setResult(RESULT_OK, output);
                        finish();
                    }
                }
            });
        }
    }
}


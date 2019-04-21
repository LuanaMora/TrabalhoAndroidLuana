package com.android.calcularfrete;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.calcularfrete.model.Cep;
import com.android.calcularfrete.model.Cidade;
import com.android.calcularfrete.model.Estado;
import com.android.calcularfrete.util.Dados;
import com.android.calcularfrete.util.Mensagem;
import com.android.calcularfrete.util.TipoMensagem;

import java.util.ArrayList;
import java.util.List;

public class Main_CadEstadoActivity extends AppCompatActivity {

    private EditText etCodigo, etUf, etDescricao, etValor;
    private Button btSalvar, btCancelar;
    private TextView tvTituloCadasto;
    private Spinner spCadastro;
    private int tipoTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cad_estado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadComponents();
        carregaEventos();

        tipoTela = (int) getIntent().getExtras().get("TIPO");
        if (tipoTela == 1) {
            tvTituloCadasto.setText("ESTADO");
            spCadastro.setVisibility(View.INVISIBLE);

        } else if (tipoTela == 2) {
            tvTituloCadasto.setText("CIDADE");
            etDescricao.setVisibility(View.VISIBLE);
            etUf.setVisibility(View.GONE);
            etValor.setVisibility(View.GONE);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Dados.estadoList);//Seta um array adapter com a lista de estado para aparecer no spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCadastro.setAdapter(adapter);

        } else if (tipoTela == 3) {
            tvTituloCadasto.setText("CEP");
            etCodigo.setHint("CEP");
            etUf.setVisibility(View.GONE);
            etDescricao.setVisibility(View.GONE);
            etValor.setVisibility(View.GONE);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Dados.cidadeListAux); //Seta um array adapter com a lista de cidade para aparecer no spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCadastro.setAdapter(adapter);
        }

    }

    private void loadComponents() {
        etCodigo = findViewById(R.id.etCodigo);
        etUf = findViewById(R.id.etUf);
        etDescricao = findViewById(R.id.etDescricao);
        etValor = findViewById(R.id.etValor);
        btSalvar = findViewById(R.id.btSalvar);
        btCancelar = findViewById(R.id.btCancelar);
        tvTituloCadasto = findViewById(R.id.tvTituloCadastro);
        spCadastro = findViewById(R.id.spCadastro);


    }

    private void carregaEventos() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipoTela == 1) {
                    boolean controlaSalvar = true;
                    try {
                        Estado estado = new Estado();
                        estado.setCodigo(Integer.parseInt(etCodigo.getText().toString()));
                        estado.setUf(etUf.getText().toString());
                        estado.setDescricao(etDescricao.getText().toString());
                        estado.setValorFrete((Double.parseDouble(etValor.getText().toString())));
                        for (Estado e : Dados.estadoList) {
                            if (e.getCodigo() == Integer.parseInt(etCodigo.getText().toString())) {
                                Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "CÓDIGO JÁ CADASTRADO, TENTE OUTRO CÓDIGO.", TipoMensagem.ALERTA);
                                controlaSalvar = false;

                            }

                            if ((e.getUf().equalsIgnoreCase(etUf.getText().toString())) ||
                                    (e.getDescricao().equalsIgnoreCase(etDescricao.getText().toString()))) {
                                Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "ESTADO JÁ CADASTRADO, TENTE NOVAMENTE", TipoMensagem.ALERTA);
                                controlaSalvar = false;
                            }
                        }

                        if (controlaSalvar) {
                            Dados.estadoList.add(estado);
                            Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Estado SALVO com sucesso!", TipoMensagem.SUCESSO);


                        }
                    } catch (Exception ex) {
                        Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Não foi possível salvar!", TipoMensagem.ERRO);
                        controlaSalvar = false;
                    }

                } else if (tipoTela == 2) {
                    boolean controlaSalvar = true;
                    try {
                        Cidade cidade = new Cidade();
                        cidade.setCodigo(Integer.parseInt(etCodigo.getText().toString()));
                        cidade.setDescricao(etDescricao.getText().toString());
                        Estado conteudoSp = (Estado) spCadastro.getSelectedItem();
                        cidade.setEstado(conteudoSp);
                        for (Estado e : Dados.estadoList) {
                            if (e.getCodigo() == conteudoSp.getCodigo()) {
                                if (e.getCidadeList() == null)
                                    e.setCidadeList(new ArrayList<Cidade>());

                                e.getCidadeList().add(cidade);

                                Dados.cidadeList.add(cidade);
                                Dados.cidadeListAux.add(cidade);
                                Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Cidade SALVA com sucesso!", TipoMensagem.SUCESSO);

                            }
                        }
                    } catch (Exception ex) {
                        Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Não foi possível salvar!", TipoMensagem.ERRO);
                    }
                } else if (tipoTela == 3) {
                    try {
                        Cep cep = new Cep();
                        cep.setCodigo(etCodigo.getText().toString());
                        Cidade conteudoSp = (Cidade) spCadastro.getSelectedItem();
                        cep.setCidade(conteudoSp);
                        for (Cidade c : Dados.cidadeListAux) {
                            if (c.getCodigo() == conteudoSp.getCodigo()) {
                                if (c.getCepList() == null)
                                    c.setCepList(new ArrayList<Cep>());

                                c.getCepList().add(cep);

                                if (c.getCodigo() != Integer.parseInt(etCodigo.getText().toString())) {
                                    Dados.cepList.add(cep);
                                    Dados.cepListAux.add(cep);
                                    Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Cep SALVO com sucesso!", TipoMensagem.SUCESSO);

                                }
                            }
                        }
                    } catch (Exception ex) {
                        Mensagem.ExibirMensagem(Main_CadEstadoActivity.this, "Não foi possível salvar!", TipoMensagem.ERRO);
                    }

                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}

package com.android.calcularfrete;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.calcularfrete.model.Cep;
import com.android.calcularfrete.model.Cidade;
import com.android.calcularfrete.model.Estado;
import com.android.calcularfrete.util.Dados;
import com.android.calcularfrete.util.Mensagem;
import com.android.calcularfrete.util.TipoMensagem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView lbUfOrigem, lbCidadeOrigem, lbUfDestino, lbCidadeDestino, tvResultadoFrete, lbCepO, lbCepD;
    private ImageButton ibUfO, ibCidadeO, ibUfD, ibCidadeD, ibCepO, ibCepD;
    private TextView tvValor;
    private EditText etPeso;
    private Button btCalcular;
    private Double peso, valorFrete;
    private final int LOVFRETE = 1;
    public static boolean ESTADOORIGEM = false;
    public static boolean ESTADODESTINO = false;
    public static boolean CIDADEORIGEM = false;
    public static boolean CIDADEDESTINO = false;
    public static boolean CEPORIGEM = false;
    public static boolean CEPDESTINO = false;
    public static List<Cidade> cidadeListAux = new ArrayList<Cidade>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        populaComponentes();



        carregaEventos();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPeso.getText().length() > 0) {
                    double campoPeso, campoValor, resultado;
                    campoPeso = Double.parseDouble(etPeso.getText().toString());
                    campoValor = Double.parseDouble(tvValor.getText().toString());
                    resultado = campoValor * campoPeso;
                    tvResultadoFrete.setText("Resultado: " + resultado);
                } else {
                    Mensagem.ExibirMensagem(MainActivity.this, "O campo está vazio", TipoMensagem.ALERTA);
                }
            }
        });
    }

    private void carregaEventos() {
        ibUfO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "ESTADO");
                startActivityForResult(intent, LOVFRETE);
                ESTADOORIGEM = true;

            }
        });
        ibUfD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "ESTADO");
                startActivityForResult(intent, LOVFRETE);
                ESTADODESTINO = true;
            }
        });

        ibCidadeO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "CIDADE");
                startActivityForResult(intent, LOVFRETE);
                CIDADEORIGEM = true;
            }
        });

        ibCidadeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "CIDADE");
                startActivityForResult(intent, LOVFRETE);
                CIDADEDESTINO = true;
            }
        });
        ibCepO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "CEP");
                startActivityForResult(intent, LOVFRETE);
                CEPORIGEM = true;
            }
        });

        ibCepD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LovFrete.class);
                intent.putExtra("TIPO", "CEP");
                startActivityForResult(intent, LOVFRETE);
                CEPDESTINO = true;

            }
        });

    }


    private void loadComponents() {
        lbUfOrigem = findViewById(R.id.lbUfOrigem);
        lbCidadeOrigem = findViewById(R.id.lbCidadeOrigem);
        lbUfDestino = findViewById(R.id.lbUfDestino);
        lbCidadeDestino = findViewById(R.id.lbCidadeDestino);
        ibUfO = findViewById(R.id.ibUfO);
        ibCidadeO = findViewById(R.id.ibCidadeO);
        ibUfD = findViewById(R.id.ibUfD);
        ibCidadeD = findViewById(R.id.ibCidadeD);
        tvValor = findViewById(R.id.tvValor);
        etPeso = findViewById(R.id.etPeso);
        btCalcular = findViewById(R.id.btCalcular);
        tvResultadoFrete = findViewById(R.id.tvresultadoFrete);
        ibCepO = findViewById(R.id.ibCepO);
        ibCepD = findViewById(R.id.ibCepD);
        lbCepO = findViewById(R.id.lbCepO);
        lbCepD = findViewById(R.id.lbCepD);
    }

    private void populaComponentes() {

        //List<Cidade> cidadeList = new ArrayList<Cidade>();
        //List<Cep> cepList = new ArrayList<Cep>();

        Estado estado = new Estado();
        estado.setCodigo(1);
        estado.setDescricao("Paraná");
        estado.setUf("PR");
        estado.setValorFrete(200.00);


        Cidade cidade = new Cidade();
        cidade.setCodigo(2);
        cidade.setDescricao("Tupãssi");
        //cidade.setCepList(cepList);
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);


        Cep cep = new Cep();
        cep.setCodigo("85945-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(3);
        cidade.setDescricao("Toledo");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("85500-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);

        cep = new Cep();
        cep.setCodigo("95123-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);

        cep = new Cep();
        cep.setCodigo("85220-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(4);
        cidade.setDescricao("Cascavel");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("85823-750");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);

        cep = new Cep();
        cep.setCodigo("85818-526");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);

        cep = new Cep();
        cep.setCodigo("85818-166");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);
        Dados.estadoList.add(estado);

        //"---------"

        estado = new Estado();
        estado.setCodigo(5);
        estado.setUf("SC");
        estado.setDescricao("Santa Catarina");
        estado.setValorFrete(300.00);

        cidade = new Cidade();
        cidade.setCodigo(6);
        cidade.setDescricao("Florianópolis");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("88058-115");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88020-230");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88090-561");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(7);
        cidade.setDescricao("Joinville");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("89209-473");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("89201-010");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("89210-485");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(8);
        cidade.setDescricao("Itajaí");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);


        cep = new Cep();
        cep.setCodigo("88303-440");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88306-020");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88311-235");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);
        Dados.estadoList.add(estado);

        //------------


        estado = new Estado();
        estado.setCodigo(3);
        estado.setUf("RS");
        estado.setDescricao("Rio Grande do Sul");
        estado.setValorFrete(450.00);

        cidade = new Cidade();
        cidade.setCodigo(4);
        cidade.setDescricao("Porto Alegre");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("75123-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("45138-230");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("99551-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(9);
        cidade.setDescricao("Caxias do Sul");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);

        cep = new Cep();
        cep.setCodigo("89289-473");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88201-010");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("89264-485");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);

        cidade = new Cidade();
        cidade.setCodigo(10);
        cidade.setDescricao("Rio Grande");
        cidade.setEstado(estado);
        estado.getCidadeList().add(cidade);


        cep = new Cep();
        cep.setCodigo("65482-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("96421-000");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);


        cep = new Cep();
        cep.setCodigo("88412-243");
        cep.setCidade(cidade);
        cidade.getCepList().add(cep);
        Dados.cidadeListAux.add(cidade);
        Dados.estadoList.add(estado);

        System.out.println(Dados.estadoList.toString());
        System.out.println(estado.getCidadeList().toString());
        System.out.println(cidade.getCepList().toString());
        System.out.println(Dados.cidadeListAux.toString());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Estado estado = (Estado) data.getExtras().get("ESTADOSELO");
            if (estado != null) {
                if (estado.getCidadeList() != null) {
                    Dados.cidadeList.clear();
                    Dados.cidadeList.addAll(estado.getCidadeList());
                    System.out.println("cidade List" + Dados.cidadeList);
                }
                lbUfOrigem.setText(estado.toString());
            }
        }

        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Cidade cidade = (Cidade) data.getExtras().get("CIDADESELO");
            if (cidade != null) {
                if (cidade.getCepList() != null) {
                    Dados.cepList.clear();
                    Dados.cepList.addAll(cidade.getCepList());

                }
                lbCidadeOrigem.setText(cidade.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Estado estado = (Estado) data.getExtras().get("ESTADOSELD");
            if (estado != null) {
                Dados.cidadeList.clear();
                Dados.cidadeList.addAll(estado.getCidadeList());
                lbUfDestino.setText(estado.toString());
                tvValor.setText(String.valueOf(estado.getValorFrete()));
                valorFrete = estado.getValorFrete();

            }
        }
        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Cidade cidade = (Cidade) data.getExtras().get("CIDADESELD");
            if (cidade != null) {
                Dados.cepList.clear();
                Dados.cepList.addAll(cidade.getCepList());

                lbCidadeDestino.setText(cidade.toString());
            }
        }
        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Cep cep = (Cep) data.getExtras().get("CEPSELO");
            if (cep != null) {
                lbCepO.setText(cep.toString());
            }
        }
        if (resultCode == RESULT_OK && requestCode == LOVFRETE) {
            Cep cep = (Cep) data.getExtras().get("CEPSELD");
            if (cep != null) {
                lbCepD.setText(cep.toString());
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_estado) { //ESTADO 1
            intent = new Intent(MainActivity.this, Main_CadEstadoActivity.class); //1º parametro: aonde estou. 2º parametro: aonde quero ir
            intent.putExtra("TIPO", 1);
            startActivity(intent); //aqui abre a tela
        } else if (id == R.id.nav_cidade) {
            intent = new Intent(MainActivity.this, Main_CadEstadoActivity.class);
            intent.putExtra("TIPO", 2); //CIDADE 2
            startActivity(intent);
        } else if (id == R.id.nav_cep) {
            intent = new Intent(MainActivity.this, Main_CadEstadoActivity.class);
            intent.putExtra("TIPO", 3); //CEP 3
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

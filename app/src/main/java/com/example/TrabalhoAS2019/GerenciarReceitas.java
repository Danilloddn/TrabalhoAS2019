package com.example.TrabalhoAS2019;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.TrabalhoAS2019.Adapter.ExpLVAdapter;
import com.example.trabalhoas.R;
import com.example.TrabalhoAS2019.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GerenciarReceitas extends AppCompatActivity {

    /*private ListView lsvFontes;
    private List<ModeloFonte> fonteList;
    private AdapterListaFonte adapterListaFonte;*/


    private ExpandableListView expLV;
    private ExpLVAdapter adapter;
    private ArrayList<String> listCategoria;
    private Map<String,ArrayList<String>> mapChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_receita);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        ConexaoSQlite conexaoSQlite = ConexaoSQlite.getInstanciaConexao(this);

        //listarfontes();
        listaviewofview();
        Button btncalendario = (Button) findViewById(R.id.btncalendario);
        btncalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario();
            }
        });
        Button btnfonte = (Button) findViewById(R.id.adicionarfonte);
        btnfonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogaddfontesembanco();
            }
        });
        Button btnexcluir = (Button) findViewById(R.id.excluirfonte);
        btnexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirdfontesembanco();
            }
        });

        Button btneditar= (Button) findViewById(R.id.editarfonte);
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarfontesembanco();
            }
        });




        /*
        SearchView searchView = (SearchView) findViewById(R.id.buscarfontes);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return true;
            }
        });*/

    }
/*
    private void alertdialogaddfonte() {
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome da fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloFonte fonteACadastrar = new ModeloFonte();

                fonteACadastrar.setDescricao(fontetxt.getText().toString());

                if (fontetxt.getText().length() != 0){
                    FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceitas.this));
                    fonteCtrl.salvarFonteCtrl(fonteACadastrar);
                    Toast.makeText(GerenciarReceitas.this, "Fonte cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(GerenciarReceitas.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogaddfonte();
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //CRIAR DIALOG
        final AlertDialog ad = builder.create();
        ad.show();
        fontetxt.setText("");
    }

    private void alertdialogeeditarfonte(final int cod_fonte) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceitas.this));
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome da fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloFonte fonteACadastrar = new ModeloFonte();
                fonteACadastrar.setCodfonte(cod_fonte);
                fonteACadastrar.setDescricao(fontetxt.getText().toString());
                if (fontetxt.getText().length() != 0){
                    fonteCtrl.atualizarFonteCtrl(fonteACadastrar);
                    Toast.makeText(GerenciarReceitas.this, "Fonte alterada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(GerenciarReceitas.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditarfonte(cod_fonte);
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //CRIAR DIALOG
        final AlertDialog ad = builder.create();
        ad.show();
        fontetxt.setText("");
    }

    private void alertdialogexcluirfonte(final int cod_fonte) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceitas.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente exluir esta fonte?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fonteCtrl.excluirFonteCtrl(cod_fonte);
                Toast.makeText(GerenciarReceitas.this, "Fonte removida com sucesso!",Toast.LENGTH_SHORT).show();;
                listarfontes();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }

    public void listarfontes(){

        //BUSCAR TODOS AS FONTES DO BANCO

        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceitas.this));

        this.fonteList = new ArrayList<>();
        fonteList = fonteCtrl.getListaFontesCtrl();

        this.lsvFontes = (ListView) findViewById(R.id.listarfontes);

        this.adapterListaFonte = new AdapterListaFonte(GerenciarReceitas.this, fonteList);

        this.lsvFontes.setAdapter(this.adapterListaFonte);

        this.lsvFontes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaFonte.select(posicao);

                ModeloFonte fonteSelecionada = (ModeloFonte) adapterListaFonte.getItem(posicao);
                final int cod_fonte = fonteSelecionada.getCodfonte();

                Button btnexcluir = findViewById(R.id.excluirfonte);

                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirfonte(cod_fonte);
                    }
                });

                Button btneditar = findViewById(R.id.editarfonte);
                btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditarfonte(cod_fonte);
                    }
                });
            }
        });
    }

    private void searchContact(String keyword) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceitas.this));
        List<ModeloFonte> fontes = (List<ModeloFonte>) fonteCtrl.procurarControler(keyword);
        if (fontes != null) {
            this.lsvFontes = (ListView) findViewById(R.id.listarfontes);
            lsvFontes.setAdapter(new AdapterListaFonte(getApplicationContext(),fontes));
        }
    }
*/
    public void calendario(){
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite a data que deseja pesquisar: ");
        fontetxt = new EditText(this);
        fontetxt.setInputType(InputType.TYPE_CLASS_DATETIME);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (fontetxt.getText().length() != 0){
                }
                else {
                    Toast.makeText(GerenciarReceitas.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();
        ad.show();
    }

    public void alertdialogaddfontesembanco(){
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o da nova fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (fontetxt.getText().length() != 0){
                    Toast.makeText(GerenciarReceitas.this, "Fonte inserida com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GerenciarReceitas.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();
        ad.show();
    }
    public void excluirdfontesembanco(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Deseja realmente excluir esta fonte?");

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GerenciarReceitas.this, "Fonte excluida com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();
        ad.show();
    }
    public void editarfontesembanco(){
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome da fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (fontetxt.getText().length() != 0){
                    Toast.makeText(GerenciarReceitas.this, "Fonte modificada com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GerenciarReceitas.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builder.create();
        ad.show();
    }

    private void listaviewofview(){
        expLV= (ExpandableListView) findViewById(R.id.listarfontes);
        listCategoria = new ArrayList<>();
        mapChild = new HashMap<>();

        carregardados();
    }

    private void carregardados(){
        Facade facade = new Facade();

        ArrayList<String> salario;
        ArrayList<String> pensao;
        ArrayList<String> aluguel;

        salario = facade.carregarSalario();
        pensao = facade.carregarPensao();
        aluguel = facade.carregarAluguel();
        listCategoria.add("Salário");
        listCategoria.add("Pensão");
        listCategoria.add("Aluguel");

        mapChild.put(listCategoria.get(0),salario);
        mapChild.put(listCategoria.get(1),pensao);
        mapChild.put(listCategoria.get(2),aluguel);


        adapter = new ExpLVAdapter(listCategoria, mapChild, this);
        expLV.setAdapter(adapter);

    }

}

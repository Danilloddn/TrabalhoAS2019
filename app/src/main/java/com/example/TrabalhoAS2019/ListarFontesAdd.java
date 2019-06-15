package com.example.TrabalhoAS2019;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.TrabalhoAS2019.Adapter.AdapterListaFonte;
import com.example.TrabalhoAS2019.Controller.FonteCtrl;
import com.example.TrabalhoAS2019.Model.ModeloFonte;
import com.example.TrabalhoAS2019.dbhelper.ConexaoSQlite;
import com.example.trabalhoas.R;

import java.util.List;

public class ListarFontesAdd extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_fontes_add);
        getSupportActionBar().setTitle("");
        listarfontes();
        Button addfonte = (Button) findViewById(R.id.adicionarfonte3);
        addfonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogaddfonte();
                listarfontes();
            }
        });
    }

    public void listarfontes(){

        ListView lsvFontes;
        List<ModeloFonte> fonteList;
        final AdapterListaFonte adapterListaFonte;

        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(ListarFontesAdd.this));

        fonteList = fonteCtrl.getListaFontesCtrl();

        lsvFontes = (ListView) findViewById(R.id.listarfonteadd);

        adapterListaFonte = new AdapterListaFonte(ListarFontesAdd.this, fonteList);

        lsvFontes.setAdapter(adapterListaFonte);

        lsvFontes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {
                adapterListaFonte.select(posicao);
                ModeloFonte fonteSelecionada = (ModeloFonte) adapterListaFonte.getItem(posicao);
                final int cod_fonte = fonteSelecionada.getCodfonte();
                exibirmsg();
            }
        });
    }

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
                    FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(ListarFontesAdd.this));
                    fonteCtrl.salvarFonteCtrl(fonteACadastrar);
                    Toast.makeText(ListarFontesAdd.this, "Fonte cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(ListarFontesAdd.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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

    private void exibirmsg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListarFontesAdd.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Deseja realmente associar este valor a esta receita?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListarFontesAdd.this, "Valor adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
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
}

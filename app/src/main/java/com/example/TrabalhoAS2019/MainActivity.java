package com.example.TrabalhoAS2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.TrabalhoAS2019.Model.ModeloFonte;
import com.example.trabalhoas.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        Button addreceita = (Button) findViewById(R.id.addreceita);
        addreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText fontetxt;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                builder.setMessage("Digite o valor da receita: ");
                fontetxt = new EditText(MainActivity.this);
                builder.setView(fontetxt);
                fontetxt.setInputType(InputType.TYPE_CLASS_NUMBER);

                //OK
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModeloFonte fonteACadastrar = new ModeloFonte();
                        fonteACadastrar.setDescricao(fontetxt.getText().toString());
                        if (fontetxt.getText().length() != 0){
                            Intent it = new Intent (MainActivity.this, ListarFontesAdd.class);
                            startActivity(it);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void gerenciarreceitas (View view){
        Intent it = new Intent (MainActivity.this, GerenciarReceitas.class);
        startActivity(it);
    }
}

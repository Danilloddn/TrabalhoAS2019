package com.example.TrabalhoAS2019.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQlite extends SQLiteOpenHelper {

    private static ConexaoSQlite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "bl_fontes_app";

    public ConexaoSQlite(Context context){
        super(context,NOME_DB,null,VERSAO_DB);
    }

    public static ConexaoSQlite getInstanciaConexao(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQlite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTabelaFonte =
                "CREATE TABLE fonte" +
                        "(" +
                        "cod_fonte INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "descricao TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sqlTabelaFonte);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

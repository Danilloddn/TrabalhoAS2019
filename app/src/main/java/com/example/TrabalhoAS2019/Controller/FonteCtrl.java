package com.example.TrabalhoAS2019.Controller;

import com.example.TrabalhoAS2019.DAO.FonteDAO;
import com.example.TrabalhoAS2019.Model.ModeloFonte;
import com.example.TrabalhoAS2019.dbhelper.ConexaoSQlite;

import java.util.List;

public class FonteCtrl {

    private final FonteDAO fonteDAO;

    public FonteCtrl(ConexaoSQlite pConexaoSQLite){
        fonteDAO = new FonteDAO(pConexaoSQLite);
    }

    public long salvarFonteCtrl(ModeloFonte f){
        return this.fonteDAO.salvarFonteDAO(f);
    }

    public List<ModeloFonte> getListaFontesCtrl(){
        return this.fonteDAO.getListaFontesDAO();
    }

    public boolean excluirFonteCtrl(long fcodFonte){
        return this.fonteDAO.excluirFonteDAO(fcodFonte);
    }

    public boolean atualizarFonteCtrl (ModeloFonte f){
        return this.fonteDAO.atualizarFonteDAO(f);
    }

    public List<ModeloFonte> procurarControler(String keyword){
        return this.fonteDAO.search(keyword);
    }

}

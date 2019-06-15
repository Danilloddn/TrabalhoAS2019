package com.example.TrabalhoAS2019;

import java.util.ArrayList;

public class Facade {

    public String[] getStringReceita(){
        String[] receita = new String[]{
                "Salário","Pensão","Aluguel_1","Aluguel_2","Aluguel_3","Aluguel_4","Aluguel_5"
        };

        return receita;
    }


    public ArrayList<String> carregarSalario(){
        ArrayList <String> listSalario = new ArrayList<>();

        listSalario.add("350,00                                            01/06/2019");
        listSalario.add("350,00                                            01/07/2019");
        listSalario.add("350,00                                            01/08/2019");
        listSalario.add("350,00                                            01/09/2019");

        return listSalario;
    }

    public ArrayList<String> carregarPensao(){
        ArrayList <String> listPensao = new ArrayList<>();

        listPensao.add("350,00                                             01/06/2019");
        listPensao.add("350,00                                             01/07/2019");
        listPensao.add("350,00                                             01/08/2019");
        listPensao.add("350,00                                             01/09/2019");

        return listPensao;
    }


    public ArrayList<String> carregarAluguel(){
        ArrayList <String> listAluguel = new ArrayList<>();

        listAluguel.add("350,00                                             01/06/2019");
        listAluguel.add("350,00                                             01/07/2019");
        listAluguel.add("350,00                                             01/08/2019");
        listAluguel.add("350,00                                             01/09/2019");


        return listAluguel;
    }
}

package com.mikaelmello.argumentopas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMenu extends Activity {

    public Button cursosButton;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity_menu);

        initiateSpinners();
        cursosButton = (Button)findViewById(R.id.escolherCursos);

        cursosButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Spinner idiomaUm = (Spinner)findViewById(R.id.spinnerLinguaUm);
                        String idioma1 = idiomaUm.getSelectedItem().toString();
                        EditText ebUm = (EditText)findViewById(R.id.ebUm);
                        String escBruto1 = (ebUm.getText().toString()).replace(',', '.');
                        EditText redaçãoUm = (EditText)findViewById(R.id.redaçãoUm);
                        String redação1 = (redaçãoUm.getText().toString()).replace(',', '.');

                        Spinner idiomaDois = (Spinner)findViewById(R.id.spinnerLinguaDois);
                        String idioma2 = idiomaDois.getSelectedItem().toString();
                        EditText ebDois = (EditText)findViewById(R.id.ebDois);
                        String escBruto2 = (ebDois.getText().toString()).replace(',', '.');
                        EditText redaçãoDois = (EditText)findViewById(R.id.redaçaoDois);
                        String redação2 = (redaçãoDois.getText().toString()).replace(',', '.');

                        Spinner idiomaTrês = (Spinner)findViewById(R.id.spinnerLinguaTrês);
                        String idioma3 = idiomaTrês.getSelectedItem().toString();

                        Spinner sistemaCotas = (Spinner)findViewById(R.id.spinnerSistema);
                        String cotas = sistemaCotas.getSelectedItem().toString();
                        Spinner campus = (Spinner)findViewById(R.id.spinnerCampus);
                        String campusStr = campus.getSelectedItem().toString();
                        int test[] = {count(escBruto1, ','),
                                count(escBruto1, '.'),
                                count(escBruto2, ','),
                                count(escBruto2, '.'),
                                count(redação1, ','),
                                count(redação1, '.'),
                                count(redação2, ','),
                                count(redação2, '.')};

                        if(isEmpty(ebUm)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nEscore Bruto do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(ebDois)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nEscore Bruto do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(redaçãoUm)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nRedação do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(redaçãoDois)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nRedação do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(test[0] > 1 || test[1] > 1 || test[0] + test[1] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo\n de EB do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(test[2] > 1 || test[3] > 1 || test[2] + test[3] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo\n de EB do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(test[4] > 1 || test[5] > 1 || test[4] + test[5] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo de\nRedação do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(test[6] > 1 || test[7] > 1 || test[6] + test[7] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo de\nRedação do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(escBruto1) > 85) Toast.makeText(getApplicationContext(),"Valor de Escore Bruto\ndo PAS 1 acima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(escBruto2) >= 100) Toast.makeText(getApplicationContext(),"Valor de Escore Bruto\ndo PAS 2 acima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(redação1) > 10) Toast.makeText(getApplicationContext(),"Valor de Redação do PAS 1\nacima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(redação2) > 10) Toast.makeText(getApplicationContext(),"Valor de Redação do PAS 2\nacima do limite.", Toast.LENGTH_LONG).show();
                        else { intent = new Intent(ActivityMenu.this, ActivityCursos.class);
                        intent.putExtra("idioma1", idioma1);
                        intent.putExtra("redação1", redação1);
                        intent.putExtra("eb1", escBruto1);
                        intent.putExtra("idioma2", idioma2);
                            intent.putExtra("redação2", redação2);
                            intent.putExtra("idioma3", idioma3);
                        intent.putExtra("eb2", escBruto2);
                        intent.putExtra("cotas", cotas);
                        intent.putExtra("campus", campusStr);
                            startActivity(intent); }
                    }
                });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private void initiateSpinners() {

        Spinner spinnerCampus = (Spinner)findViewById(R.id.spinnerCampus);
        final String[] allCampuses = {"Darcy Ribeiro", "Planaltina", "Ceilândia", "Gama"};

        Spinner spinnerSistema = (Spinner) findViewById(R.id.spinnerSistema);
        final String[] allSistemas = {
                "Nenhum",
                "Negros",
                "Rede Pública - ≤ 1,5 Salário Mínimo - PPI",
                "Rede Pública - ≤ 1,5 Salário Mínimo - NÃO PPI",
                "Rede Pública - > 1,5 Salário Mínimo - PPI",
                "Rede Pública - > 1,5 Salário Mínimo - NÃO PPI"};

        Spinner spinnerLinguaUm = (Spinner)findViewById(R.id.spinnerLinguaUm);
        final String[] idiomasPAS = {"Espanhol", "Inglês", "Francês"};
        Spinner spinnerLinguaDois = (Spinner)findViewById(R.id.spinnerLinguaDois);
        Spinner spinnerLinguaTrês = (Spinner)findViewById(R.id.spinnerLinguaTrês);

        ArrayAdapter<String> adapterCampus = new ArrayAdapter<String>(ActivityMenu.this,
                R.layout.spinner_item,allCampuses);
        adapterCampus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampus.setAdapter(adapterCampus);

        ArrayAdapter<String> adapterCotas = new ArrayAdapter<String>(ActivityMenu.this,
                R.layout.spinner_item,allSistemas);
        adapterCotas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSistema.setAdapter(adapterCotas);

        ArrayAdapter<String> adapterLinguas = new ArrayAdapter<String>(ActivityMenu.this,
                R.layout.spinner_item,idiomasPAS);
        adapterLinguas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLinguaUm.setAdapter(adapterLinguas);
        spinnerLinguaDois.setAdapter(adapterLinguas);
        spinnerLinguaTrês.setAdapter(adapterLinguas);
    }
    /*
      * JBoss, Home of Professional Open Source
      * Copyright 2005, JBoss Inc., and individual contributors as indicated
      * by the @authors tag. See the copyright.txt in the distribution for a
      * full listing of individual contributors.
      */
    public static int count(final String string, final String substring)
    {
        int count = 0;
        int idx = 0;

        while ((idx = string.indexOf(substring, idx)) != -1)
        {
            idx++;
            count++;
        }

        return count;
    }

    public static int count(final String string, final char c)
    {
        return count(string, String.valueOf(c));
    }
}

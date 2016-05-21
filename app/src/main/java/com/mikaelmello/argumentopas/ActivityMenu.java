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

    // Button to initiate the next activity
    public Button cursosButton;

    // The next activity
    public Intent intent;

    private Spinner spinnerCampus;
    private Spinner spinnerCotas;
    private Spinner spinnerL1;
    private Spinner spinnerL2;
    private Spinner spinnerL3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_activity_menu);

        // Method to generate the options in the spinners
        initiateSpinners();

        // Initializing the variable to the button in android_activity_menu.xml
        cursosButton = (Button)findViewById(R.id.escolherCursos);

        // Setting listener to a click on the button
        cursosButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        // Getting the selected items in each spinner
                        // Also replacing commas for dots in EditTexts to be able to convert them
                        // to floats/doubles.

                        // Getting selected items from spinners as Strings.
                        String idioma1 = spinnerL1.getSelectedItem().toString();
                        String idioma2 = spinnerL2.getSelectedItem().toString();
                        String idioma3 = spinnerL3.getSelectedItem().toString();
                        String cotas = spinnerCotas.getSelectedItem().toString();
                        String campusStr = spinnerCampus.getSelectedItem().toString();

                        // Getting written scores as strings and removing commas if there are any.
                        EditText ebUm = (EditText)findViewById(R.id.ebUm);
                        EditText redaçãoUm = (EditText)findViewById(R.id.redaçãoUm);

                        String escBruto1 = (ebUm.getText().toString()).replace(',', '.');
                        String redação1 = (redaçãoUm.getText().toString()).replace(',', '.');

                        EditText ebDois = (EditText)findViewById(R.id.ebDois);
                        EditText redaçãoDois = (EditText)findViewById(R.id.redaçaoDois);

                        String escBruto2 = (ebDois.getText().toString()).replace(',', '.');
                        String redação2 = (redaçãoDois.getText().toString()).replace(',', '.');

                        // Array storing the number of dots in each numeric field to check later.
                        int test[] = {count(escBruto1, '.'),
                                count(escBruto2, '.'),
                                count(redação1, '.'),
                                count(redação2, '.')};

                        // Testing of multiple conditions that may cause the app to crash or to
                        // not work properly. They are: empty fields or invalid numeric input.
                        // TODO: Handle error where the input of a comma crashes the app.
                        if(isEmpty(ebUm)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nEscore Bruto do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(ebDois)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nEscore Bruto do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(redaçãoUm)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nRedação do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(isEmpty(redaçãoDois)) Toast.makeText(getApplicationContext(),"Preencha o campo de\nRedação do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(test[0] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo\n de EB do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(test[1] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo\n de EB do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(test[2] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo de\nRedação do PAS 1.", Toast.LENGTH_LONG).show();
                        else if(test[3] > 1) Toast.makeText(getApplicationContext(),"Formato inválido no campo de\nRedação do PAS 2.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(escBruto1) > 85) Toast.makeText(getApplicationContext(),"Valor de Escore Bruto\ndo PAS 1 acima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(escBruto2) >= 100) Toast.makeText(getApplicationContext(),"Valor de Escore Bruto\ndo PAS 2 acima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(redação1) > 10) Toast.makeText(getApplicationContext(),"Valor de Redação do PAS 1\nacima do limite.", Toast.LENGTH_LONG).show();
                        else if(Float.parseFloat(redação2) > 10) Toast.makeText(getApplicationContext(),"Valor de Redação do PAS 2\nacima do limite.", Toast.LENGTH_LONG).show();
                        else {
                            // If everything is fine, add everything to the new intent
                            // and start the next activity

                            intent = new Intent(ActivityMenu.this, ActivityCursos.class);
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

    // Checks if an EditText is empty.
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

    // Initiates the spinners
    private void initiateSpinners() {

        // Initializing spinner of all available Campuses
        spinnerCampus = (Spinner)findViewById(R.id.spinnerCampus);
        final String[] allCampuses = {"Darcy Ribeiro", "Planaltina", "Ceilândia", "Gama"};

        ArrayAdapter<String> adapterCampus = new ArrayAdapter<>(ActivityMenu.this,
                R.layout.spinner_item,allCampuses);
        adapterCampus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampus.setAdapter(adapterCampus);

        // Spinner of different quotas.
        spinnerCotas = (Spinner) findViewById(R.id.spinnerSistema);
        final String[] allSistemas = {
                "Nenhum",
                "Negros",
                "Rede Pública - ≤ 1,5 Salário Mínimo - PPI",
                "Rede Pública - ≤ 1,5 Salário Mínimo - NÃO PPI",
                "Rede Pública - > 1,5 Salário Mínimo - PPI",
                "Rede Pública - > 1,5 Salário Mínimo - NÃO PPI"};

        ArrayAdapter<String> adapterCotas = new ArrayAdapter<>(ActivityMenu.this,
                R.layout.spinner_item,allSistemas);
        adapterCotas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCotas.setAdapter(adapterCotas);

        // The three available idioms (Spanish, English and French)
        final String[] idiomasPAS = {"Espanhol", "Inglês", "Francês"};

        // Initializing the three variables with their respective spinners.
        spinnerL1 = (Spinner)findViewById(R.id.spinnerLinguaUm);
        spinnerL2 = (Spinner)findViewById(R.id.spinnerLinguaDois);
        spinnerL3 = (Spinner)findViewById(R.id.spinnerLinguaTrês);

        ArrayAdapter<String> adapterLinguas = new ArrayAdapter<>(ActivityMenu.this,
                R.layout.spinner_item,idiomasPAS);
        adapterLinguas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Using the same adapter to the three spinners.
        spinnerL1.setAdapter(adapterLinguas);
        spinnerL2.setAdapter(adapterLinguas);
        spinnerL3.setAdapter(adapterLinguas);
    }

    /*
      * JBoss, Home of Professional Open Source
      * Copyright 2005, JBoss Inc., and individual contributors as indicated
      * by the @authors tag. See the copyright.txt in the distribution for a
      * full listing of individual contributors.
      */

    // TODO: replace this method for a better suitable one.
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

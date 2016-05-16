package com.mikaelmello.argumentopas;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class ActivityResultado extends AppCompatActivity {

    private Bundle cursos;
    private Bundle dados;
    private Intent intent;
    MyCustomAdapter dataAdapter = null;
    private double eb[] = new double[2];
    private double red[] = new double[2];
    private double medialingua[][] = new double[3][3];
    private double dplingua[][] = new double[3][3];
    private double mediaescbruto[] = {19.519, 20.371, 26.469};
    private double mediaredação[] = {7.099, 6.435, 6.719};
    private double DPEB[] = {11.517, 13.907, 14.742};
    private double DPR[] = {2.133, 1.785, 1.624};
    private double ARGF = 0;
    int j[] = new int[3];
    String cotas;
    int currentnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_resultado);
        intent = getIntent();
        cursos = intent.getBundleExtra("Cursos");
        dados = intent.getBundleExtra("Dados");


        if (cursos != null && dados != null ) {
            String temporary;

            // Sets the campus field
            temporary = dados.getString("campus");
            TextView textView = (TextView) findViewById(R.id.campusfinal);
            textView.setText(temporary);

            // Sets the cotas field
            cotas = dados.getString("cotas");
            textView = (TextView) findViewById(R.id.sistemacotas);
            textView.setText(cotas);

            eb[0] = Float.parseFloat(dados.getString("eb1"));
            eb[1] = Float.parseFloat(dados.getString("eb2"));
            red[0] = Float.parseFloat(dados.getString("redação1"));
            red[1] = Float.parseFloat(dados.getString("redação2"));

            String lingua1 = dados.getString("idioma1");
            String lingua2 = dados.getString("idioma2");
            String lingua3 = dados.getString("idioma3");

            if(lingua1.equalsIgnoreCase("Espanhol")) j[0] = 1;
            else if(lingua1.equalsIgnoreCase("Inglês")) j[0] = 0;
            else if(lingua1.equalsIgnoreCase("Francês")) j[0] = 2;
            if(lingua2.equalsIgnoreCase("Espanhol")) j[1] = 1;
            else if(lingua2.equalsIgnoreCase("Inglês")) j[1] = 0;
            else if(lingua2.equalsIgnoreCase("Francês")) j[1] = 2;
            if(lingua3.equalsIgnoreCase("Espanhol")) j[2] = 1;
            else if(lingua3.equalsIgnoreCase("Inglês")) j[2] = 0;
            else if(lingua3.equalsIgnoreCase("Francês")) j[2] = 2;

                medialingua[0][0] = 4.488;
                medialingua[0][1] = 4.072;
                medialingua[0][2] = 5.211;
                dplingua[0][0] = 2.930;
                dplingua[0][1] = 2.826;
                dplingua[0][2] = 2.058;
                medialingua[1][0] = 4.334;
                medialingua[1][1] = 4.366;
                medialingua[1][2] = 4.075;
                dplingua[1][0] = 2.436;
                dplingua[1][1] = 2.318;
                dplingua[1][2] = 1.765;
                medialingua[2][0] = 4.897;
                medialingua[2][1] = 5.469;
                medialingua[2][2] = 3.365;
                dplingua[2][0] = 2.647;
                dplingua[2][1] = 2.003;
                dplingua[2][2] = 1.892;


            for (int i = 0; i < 2; i++) {
                int k;
                if (i == 0) k = j[0];
                else k = j[1];
                double APP = (eb[i] - medialingua[k][i] - mediaescbruto[i]) * 10 / (dplingua[k][i] + DPEB[i]);
                double APPR = (red[i] - mediaredação[i]) * 10 / DPR[i];
                double ARG = 0.9 * APP + 0.1 * APPR;
                temporary = String.format("%.3g%n", ARG);
                if (i == 0) {
                    ARGF = ARGF + ARG;
                    textView = (TextView) findViewById(R.id.argumentopas1);
                } else {
                    ARGF = ARGF + ARG + ARG;
                    textView = (TextView) findViewById(R.id.argumentopas2);
                }
                textView.setText(temporary);
            }
            ;
            temporary = String.format("%.5g%n", ARGF);
            textView = (TextView) findViewById(R.id.argumentototal);
            textView.setText(temporary);

            int lel = 0;
            currentnumber = intent.getIntExtra("Current", lel);

            Cursos curso = (Cursos) cursos.getSerializable("Curso 1");

            displayListView();

        }
        else Toast.makeText(getApplicationContext(), "Nos perdoe porém algum erro aconteceu", Toast.LENGTH_LONG).show();

    }

    private void displayListView() {

        ArrayList<Cursos> cursosList = new ArrayList<Cursos>();
        //Array list of countries
        for (int i = 1; i <= currentnumber; i++) {
            Cursos curso = (Cursos) cursos.getSerializable("Curso "+i);
            cursosList.add(curso);
        }

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_item_2, cursosList);
        ListView listView = (ListView) findViewById(R.id.cursosfinal);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }

    private class MyCustomAdapter extends ArrayAdapter<Cursos> {

        private ArrayList<Cursos> cursosList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Cursos> cursosList) {
            super(context, textViewResourceId, cursosList);
            this.cursosList = new ArrayList<Cursos>();
            this.cursosList.addAll(cursosList);
        }

        private class ViewHolder {
            TextView cursonome;
            TextView argminimo;
            TextView argfaltante;
            TextView notaescbruto;
            TextView notaredação;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.simple_list_item_2, null);

                holder = new ViewHolder();
                holder.cursonome = (TextView) convertView.findViewById(R.id.cursonome);
                holder.argminimo = (TextView) convertView.findViewById(R.id.argminimo);
                holder.argfaltante = (TextView) convertView.findViewById(R.id.argfaltante);
                holder.notaescbruto = (TextView) convertView.findViewById(R.id.notaescbruto);
                holder.notaredação = (TextView) convertView.findViewById(R.id.notaredação);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            int index = 7;
            if (cotas.equalsIgnoreCase("Negros")) index = 6;
            else if (cotas.equalsIgnoreCase("Rede Pública - ≤ 1,5 Salário Mínimo - PPI")) index = 2;
            else if (cotas.equalsIgnoreCase("Rede Pública - ≤ 1,5 Salário Mínimo - NÃO PPI")) index = 3;
            else if (cotas.equalsIgnoreCase("Rede Pública - > 1,5 Salário Mínimo - PPI")) index = 4;
            else if (cotas.equalsIgnoreCase("Rede Pública - > 1,5 Salário Mínimo - NÃO PPI")) index = 5;
            Cursos curso = cursosList.get(position);

            ArrayList<String> aCurso = curso.getCursoTotal();
            holder.cursonome.setText(aCurso.get(1));

            String argminimo1;
            double argminimo;
            if (aCurso.get(index).equalsIgnoreCase("null")) {
                argminimo1 = "Indefinido";
                argminimo = 0;
            }
            else {
                argminimo1 = aCurso.get(index);
                argminimo = Float.parseFloat(aCurso.get(index)) - ARGF;
            }
            holder.argminimo.setText(argminimo1.replace('.', ','));

            holder.argfaltante.setText(String.format("%.3g", argminimo));

            double argp3 = argminimo / 3;
            double EBF = argp3 * 0.93;
            EBF = (EBF * (dplingua[j[2]][2] + DPEB[2]) / 9) + (medialingua[j[2]][2] + mediaescbruto[2]);
            if (EBF < 0 || aCurso.get(index).equalsIgnoreCase("null")) {
                EBF = 0.1;
            }
            holder.notaescbruto.setText(String.format("%.3g", EBF));

            double REDF = (argp3 * 0.07) * DPR[2] + mediaredação[2];
            double test = (red[0] + red[1] * 2 - 20) / -3;
            if ((red[0] + red[1] * 2 < 20 && REDF < test) || aCurso.get(index).equalsIgnoreCase("null")) {
                REDF = test;
            }
            if (REDF < 0) REDF = 0.1;
            holder.notaredação.setText(String.format("%.3g", REDF));

            return convertView;

        }
    }

}

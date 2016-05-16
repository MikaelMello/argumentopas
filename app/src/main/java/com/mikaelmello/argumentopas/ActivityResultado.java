package com.mikaelmello.argumentopas;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class ActivityResultado extends AppCompatActivity {

    // Cursos, dados, intent = data from the previous activity
    private Bundle cursos;
    private Bundle dados;
    private Intent intent;

    // Adapter to initialize the results in the ListView
    MyCustomAdapter dataAdapter = null;

    // eb and red: arrays to store 1st and 2nd year scores.
    private double eb[] = new double[2];
    private double red[] = new double[2];

    // media and dp: 2D arrays to store data of all 3 different years.
    private double medialingua[][] = new double[3][3];
    private double dplingua[][] = new double[3][3];

    // Arrays from data of each year.
    private double mediaescbruto[] = {19.519, 20.371, 26.469};
    private double mediaredação[] = {7.099, 6.435, 6.719};
    private double DPEB[] = {11.517, 13.907, 14.742};
    private double DPR[] = {2.133, 1.785, 1.624};

    // Final argument
    private double ARGF = 0;

    // J is an array to store the chosen language in each year.
    // TODO: Change its name to a better one
    int j[] = new int[3];

    // Stores the chosen quota and the quantity of chosen courses.
    String cotas;
    int currentnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_resultado);

        // Initializes the intent and get bundles out.
        intent = getIntent();
        cursos = intent.getBundleExtra("Cursos");
        dados = intent.getBundleExtra("Dados");

        // TODO: Write a REAL error handler.
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

            // Turns the scores from strings to floats.
            eb[0] = Float.parseFloat(dados.getString("eb1"));
            eb[1] = Float.parseFloat(dados.getString("eb2"));
            red[0] = Float.parseFloat(dados.getString("redação1"));
            red[1] = Float.parseFloat(dados.getString("redação2"));

            // Sets the J array content to its respective chosen language in the ith year.
            // TODO: Use a loop, for god's sake.
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


            // Sets the data for each language in each year.
            // First Index: 0 = English, 1 = Spanish, 2 = French
            // Second Index: nth year = n-1 index. 1=0, 2=1, 3=2.
            // English
            medialingua[0][0] = 4.488;
            medialingua[0][1] = 4.072;
            medialingua[0][2] = 5.211;
            dplingua[0][0] = 2.930;
            dplingua[0][1] = 2.826;
            dplingua[0][2] = 2.058;
            // Spanish
            medialingua[1][0] = 4.334;
            medialingua[1][1] = 4.366;
            medialingua[1][2] = 4.075;
            dplingua[1][0] = 2.436;
            dplingua[1][1] = 2.318;
            dplingua[1][2] = 1.765;
            // French
            medialingua[2][0] = 4.897;
            medialingua[2][1] = 5.469;
            medialingua[2][2] = 3.365;
            dplingua[2][0] = 2.647;
            dplingua[2][1] = 2.003;
            dplingua[2][2] = 1.892;

            // Iterating through 1st and 2nd year to show the final argument of each year in the
            // screen. Calculation based on 2013-2015 data.
            for (int i = 0; i < 2; i++) {
                // k = Index of the language chosen in the ith year.
                int k = j[i];

                // APP = Afastamento Padronizado. Reformulated because the user does not input
                // specific data (score of the part 1 of the exam, only part 1 and 2 together).
                // TODO: Reformulate this based on the latest edital, it seems to be different
                // Link: http://www.cespe.unb.br/pas/arquivos/PAS%201%20Abt%202015-2017.pdf
                double APP = (eb[i] - medialingua[k][i] - mediaescbruto[i]) * 10 / (dplingua[k][i] + DPEB[i]);

                // APPR = Afastamento Padronizado da Redação.
                double APPR = (red[i] - mediaredação[i]) * 10 / DPR[i];

                // Calculating the argument of the current year and turning it into a string.
                double ARG = 0.9 * APP + 0.1 * APPR;
                temporary = String.format("%.3g%n", ARG);

                // Selecting the respective TextView and also adding the current argument to the
                // final argument (to be used later)
                if (i == 0) {
                    ARGF = ARGF + ARG;
                    textView = (TextView) findViewById(R.id.argumentopas1);
                } else {
                    ARGF = ARGF + ARG + ARG;
                    textView = (TextView) findViewById(R.id.argumentopas2);
                }
                textView.setText(temporary);
            }

            // Showing the current final argument (without the 3rd year of the exam).
            temporary = String.format("%.5g%n", ARGF);
            textView = (TextView) findViewById(R.id.argumentototal);
            textView.setText(temporary);

            // TODO: Find out why the hell this variable exists.
            // Not removing it now because I'm not sure if it is necessary or not for the getIntExtra()
            int lel = 0;
            currentnumber = intent.getIntExtra("Current", lel);

            displayListView();

        }
        // Worst error handling of all known history.
        else Toast.makeText(getApplicationContext(), "Nos perdoe porém algum erro aconteceu", Toast.LENGTH_LONG).show();

    }

    private void displayListView() {

        ArrayList<Cursos> cursosList = new ArrayList<Cursos>();

        // Getting the selected courses back into an array.
        for (int i = 1; i <= currentnumber; i++) {
            Cursos curso = (Cursos) cursos.getSerializable("Curso "+i);
            cursosList.add(curso);
        }

        //create an ArrayAdaptar from the Cursos Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_item_2, cursosList);
        ListView listView = (ListView) findViewById(R.id.cursosfinal);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }

    // TODO: Same as the previous activity, know properly how this works.

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

            // Index of the correct course's minimum argument to be selected, based on the quota.
            int index = 7;
            if (cotas.equalsIgnoreCase("Negros")) index = 6;
            else if (cotas.equalsIgnoreCase("Rede Pública - ≤ 1,5 Salário Mínimo - PPI")) index = 2;
            else if (cotas.equalsIgnoreCase("Rede Pública - ≤ 1,5 Salário Mínimo - NÃO PPI")) index = 3;
            else if (cotas.equalsIgnoreCase("Rede Pública - > 1,5 Salário Mínimo - PPI")) index = 4;
            else if (cotas.equalsIgnoreCase("Rede Pública - > 1,5 Salário Mínimo - NÃO PPI")) index = 5;

            // Selects the current course to be initialized in the ListView.
            Cursos curso = cursosList.get(position);

            // Method to return all the course's data into an arrayo of strings.
            ArrayList<String> aCurso = curso.getCursoTotal();

            // Sets the course's name.
            holder.cursonome.setText(aCurso.get(1));

            // Argument left to reach the minimum argument necessary to enter UnB.
            // Minimum argument - your current argument.
            String argminimo1;
            double argminimo;

            // If there's no minimum argument, we assume you do not need any score to get into
            // the University, therefore, argminimo = 0;
            boolean isNull = aCurso.get(index).equalsIgnoreCase("null");
            if (isNull) {
                argminimo1 = "Indefinido";
                argminimo = 0;
            }
            else {
                argminimo1 = aCurso.get(index);
                argminimo = Float.parseFloat(aCurso.get(index)) - ARGF;
            }

            // Replaces dots for commas (the standard in Brazil)
            holder.argminimo.setText(argminimo1.replace('.', ','));

            // Shows the argument left to reach the minimum argument.
            holder.argfaltante.setText(String.format("%.3g", argminimo));

            // Calculating the necessary Brute Score to reach the minimum score.
            // It is based on an assumption that the Essay only takes 7% of the year's final argument.
            // My personal experience shows 4% on the first year, 5% on the second year and 9%
            // on the third year. I'll probably decrease this % to 5% or 6%.

            // The argument of the third year is multiplied by 3, therefore we need to divide.
            double argp3 = argminimo / 3;

            // The brute score argument is, assumedly, 93% of the year's final argument.
            double APP = argp3 * 0.93;

            // Replacing the data and isolating the Brute Score I found this equation, which has
            // already been tested and the results were consistent with reality.
            double EBF = (APP * (dplingua[j[2]][2] + DPEB[2]) / 9) + (medialingua[j[2]][2] + mediaescbruto[2]);

            // If EBF is less than 0, it is set to 0.1 because getting a score lower than 0
            // eliminates you from the exam.
            // If isNull evaluates to True, there's no minimum argument, therefore you can score
            // anything.
            if (EBF < 0 || isNull) {
                EBF = 0.1;
            }
            holder.notaescbruto.setText(String.format("%.3g", EBF));

            // Same thing from EBF, but this time doing with the Essay score.
            double REDF = (argp3 * 0.07) * DPR[2] + mediaredação[2];

            // If you get a summed score lower than a certain number, you are also eliminated.
            double test = (red[0] + red[1] * 2 - 20) / -3;

            // If your needed score is less than the minimum one or there's no minimum argument.
            // Your needed score is the minimum one.
            if ((red[0] + red[1] * 2 < 20 && REDF < test) || isNull) {
                REDF = test;
            }

            // If even after that your needed score is below 0, it is set to 0.1.
            if (REDF < 0) REDF = 0.1;
            holder.notaredação.setText(String.format("%.3g", REDF));

            return convertView;

        }
    }

}

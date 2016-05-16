package com.mikaelmello.argumentopas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityCursos extends Activity {

    MyCustomAdapter dataAdapter = null;
    public String campus = null;
    public int currentnumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cursos);

        final Bundle bundle = getIntent().getExtras();
        String campus1 = bundle.getString("campus");
        campus = campus1;

        displayListView();

        Button myButton = (Button) findViewById(R.id.finalbutton);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle responseText = new Bundle();

                ArrayList<Cursos> cursosList = dataAdapter.cursosList;
                for (int i = 0; i < cursosList.size(); i++) {
                    Cursos curso = cursosList.get(i);
                    if (curso.isSelected()) {
                        currentnumber++;
                        //responseText.putStringArrayList("Curso" + currentnumber, curso.getCursoTotal());
                        responseText.putSerializable("Curso " + currentnumber, curso);
                    }
                }

                if (currentnumber > 5) {
                    Toast.makeText(getApplicationContext(), "Você só pode selecionar até 5 cursos por vez.", Toast.LENGTH_LONG).show();
                    currentnumber = 0;
                } else {
                    Intent intent = new Intent(ActivityCursos.this, ActivityResultado.class);
                    intent.putExtra("Current", currentnumber);
                    intent.putExtra("Cursos", responseText);
                    intent.putExtra("Dados", bundle);
                    currentnumber = 0;
                    startActivity(intent);
                }


            }
        });

    }

    private void displayListView() {

        ArrayList<Cursos> cursosList = new ArrayList<Cursos>();
        //Array list of countries

        if(campus.equalsIgnoreCase("Darcy Ribeiro")) {
            Cursos curso = new Cursos("Diurno", "Administração", "-48.068", "null", "-79.192", "-21.64", "-9.611", "45.738");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Agronomia", "-55.379", "-65.883", "-79.273", "-82.979", "-23.266", "9.355");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Agronomia", "-55.379", "-65.883", "-79.273", "-82.979", "-23.266", "9.355");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Artes Cênicas", "null", "11.02", "-87.201", "null", "50.615", "8.95");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Artes Plásticas", "-51.596", "null", "null", "-60.147", "null", "-2.355");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Arquitetura e Urbanismo", "-60.729", "null", "-39.018", "54.403", "87.992", "92.151");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Biblioteconomia", "-33.42", "-81.348", "-76.699", "-7.475", "-68.383", "-20.388");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Biotecnologia", "-42.644", "null", "-29.474", "65.916", "9.913", "73.852");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciência da Computação", "-27.969", "-68.469", "-64.013", "-20.407", "87.853", "89.07");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciência Política", "8.31", "-31.859", "-30.444", "-0.385", "49.723", "62.719");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciências Biológicas", "-39.059", "13.763", "14.971", "44.011", "54.78", "78.735");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciências Contábeis", "-56.009", "null", "-70.566", "6.23", "32.247", "46.496");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciências Econômicas", "-51.634", "-60.788", "-32.134", "47.382", "-18.18", "99.597");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciências Sociais", "-29.927", "5.047", "-66.621", "-57.128", "28.904", "45.269");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Comunicação Social", "-52.884", "-49.965", "3.071", "25.835", "68.589", "80.024");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Design", "null", "-35.904", "null", "-37.018", "-8.404", "72.248");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Direito", "6.819", "28.048", "64.631", "80.58", "87.282", "131.676");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Educação Fìsica (Bacharelado)", "-7.025", "-54.501", "-40.427", "-16.334", "-27.043", "9.244");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Educação Física (Licenciatura)", "null", "null", "-68.811", "-29.687", "-21.345", "-12.377");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Enfermagem", "-59.683", "null", "-27.413", "1.726", "51.056", "45.827");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Ambiental", "-15.991", "-58.672", "-21.748", "-32.21", "24.786", "49.36");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Civil", "-35.661", "-51.277", "43.145", "42.078", "139.754", "121.526");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia da Computação", "61.995", "-37.049", "-38.169", "70.745", "73.562", "118.465");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia de Redes de Comunicação", "null", "null", "-43.765", "-64.338", "null", "48.114");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Elétrica", "110.301", "76.966", "-22.793", "74.967", "111.882", "124.62");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Florestal", "-58.102", "null", "-71.911", "-47.149", "-8.119", "5.688");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Mecânica", "-4.808", "-17.366", "-10.001", "29.474", "91.698", "122.197");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Mecatrônica", "null", "null", "-59.701", "-21.229", "67.664", "121.089");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Engenharia Química", "-31.646", "79.223", "44.698", "38.421", "39.376", "107.706");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Estatística", "null", "null", "-74.49", "-77.892", "9.25", "41.358");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Farmácia", "-59.049", "null", "-66.973", "-33.807", "47.061", "42.952");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Filosofia", "-47.056", "null", "-63.774", "3.074", "-14.285", "16.696");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Física", "-28.929", "-48.049", "-44.188", "2.684", "83.745", "98.613");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Geofísica", "null", "null", "-38.88", "-36.692", "null", "0.974");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Geografia", "-41.086", "null", "-67.781", "-42.191", "5.138", "15.868");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Geologia", "-7.726", "null", "-39.307", "-10.732", "19.912", "52.045");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "História", "19.988", "null", "-31.458", "-21.437", "-20.633", "49.271");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Francês", "-46.212", "-39.359", "-67.447", "-36.253", "-39.633", "-34.804");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Inglês", "-10.585", "-18.924", "-9.818", "16.659", "13.283", "32.132");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Língua Est. Aplicada", "-24.901", "-53.565", "-55.635", "3.352", "-8.84", "13.92");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Português", "-29.696", "-40.627", "-19.928", "21.058", "29.214", "53.328");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - PT-BR como 2ª Lingua", "-54.34", "null", "-10.118", "-26.505", "23.248", "-72.698");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Tradução - Francês", "null", "null", "-45", "null", "8.327", "-24.35");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Letras - Tradução - Inglês", "null", "null", "19.552", "-56.475", "-7.921", "4.067");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Matemática", "-23.5", "-39.854", "-39.277", "2.321", "27.895", "30.243");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Medicina", "-7.265", "76.526", "109.085", "163.366", "164.333", "163.623");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Medicina Veterinária", "null", "-84.65", "-31.399", "0.411", "30.422", "73.206");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Museologia", "-72.08", "-55.874", "null", "null", "null", "-76.463");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Música (Bacharelado)", "null", "null", "-36.164", "null", "null", "-84.495");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Música (Licenciatura)", "null", "null", "-8.456", "null", "null", "-33.101");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Nutrição", "-46.404", "-68.201", "-11.13", "14.491", "36.506", "67.864");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Odontologia", "-17", "-41.548", "3.095", "2.995", "66.776", "85.138");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Pedagogia", "-70.376", "-58.635", "-45.9", "-18.915", "-27.497", "-20.837");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Psicologia", "-22.635", "-12.839", "7.091", "12.663", "32.992", "92.057");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Química (Bacharelado)", "9.118", "3.251", "-41.155", "32.485", "0.925", "43.913");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Química Tecnológica", "null", "null", "-34.231", "-16.402", "-39.056", "10.366");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Relações Internacionais", "2.769", "-50.99", "-19.086", "56.342", "32.54", "105.538");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Serviço Social", "-72.639", "-46.825", "-48.722", "-47.615", "57.01", "2.608");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Turismo", "null", "null", "-70.545", "-60.021", "2.32", "-33.331");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Administração (Not)", "null", "-39.666", "-55.892", "-81.867", "2.686", "37.09");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Arquitetura (Not)", "7.087", "null", "7.736", "15.411", "68.515", "88.834");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Arquivologia (Not)", "null", "null", "-29.319", "-12.786", "-35.778", "-24.207");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Artes Cênicas (Not)", "null", "null", "null", "null", "null", "-65.974");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Ciências Ambientais (Not)", "null", "null", "-39.488", "-69.172", "-45.027", "-12.868");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Ciências Biológicas (Not)", "null", "-30.027", "-69.807", "-17.062", "29.657", "22.841");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Ciências Contábeis (Not)", "-20.031", "null", "-65.43", "-22.652", "15.05", "32.707");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Computação - Licenciatura (Not)", "null", "null", "-56.103", "-36.874", "-38.146", "-47.569");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Comunicação Organizacional (Not)", "-66.908", "null", "-70.64", "-23.26", "5.593", "30.199");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Direito (Not)", "-26.727", "1.65", "24.461", "43.583", "83.076", "121.117");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Engenharia de Produção (Not)", "-46.77", "-56.413", "-39.782", "-31.756", "48.163", "78.709");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Farmácia (Not)", "null", "null", "-45.702", "-74.759", "29.87", "33.347");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Filosofia - Licenciatura (Not)", "-52.782", "null", "-58.143", "-43.726", "-16.839", "-42.035");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Física - Licenciatura (Not)", "null", "null", "-92.415", "-29.494", "0.23", "-25.602");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Gestão de Políticas Publ. (Not)", "-26.848", "-39.754", "-57.741", "-15.305", "9.598", "19.011");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Gestão do Agronegócio (Not)", "null", "null", "-55.4", "-67.29", "-8.281", "-48.372");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Gestão em Saúde Coletiva (Not)", "-52.786", "-49.969", "-68.026", "null", "-13.91", "-42.185");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "História (Not)", "-17.955", "-44.957", "-3.128", "-0.039", "7.733", "28.298");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Letras — Espanhol (Not)", "-13.303", "-31.68", "-46.007", "-3.334", "-19.467", "-24.198");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Letras — Japonês (Not)", "null", "null", "null", "-75.792", "null", "-65.633");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Letras — Português (Not)", "null", "null", "-71.133", "-57.271", "-42.422", "-27.634");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Letras — Tradução Esp. (Not)", "null", "null", "null", "null", "-60.2", "-70.669");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Matemática (Not)", "-59.848", "-32.861", "-33.36", "-43.045", "-5.126", "-7.424");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Pedagogia (Not)", "-61.849", "-72.197", "-45.36", "-29.874", "-43.772", "-31.533");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Química - Licenciatura (Not)", "null", "-80.755", "-53.911", "-52.619", "-43.81", "9.047");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Serviço Social (Not)", "null", "-33.946", "-65.344", "-64.282", "-7.695", "-25.963");
            cursosList.add(curso);
            curso = new Cursos("Noturno", "Teoria Crítica e Hist. da Arte (Not)", "null", "null", "1.618", "-38.649", "-16.58", "-33.841");
            cursosList.add(curso);
            curso = null;
        }
        else if(campus.equalsIgnoreCase("Planaltina")) {
            Cursos curso = new Cursos("Diurno", "Ciências Naturais", "-75.719", "null", "null", "-50.953", "null", "-71.268");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Gestão do Agronegócio", "null", "null", "-96.851", "-62.429", "-50.967", "-77.002");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Ciências Naturais (Not)", "-76.712", "null", "-69.328", "null", "null", "-76.345");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Gestão Ambiental (Not)", "null", "null", "-59.507", "null", "null", "-94.204");
            cursosList.add(curso);
            curso = null;
        }
        else if(campus.equalsIgnoreCase("Ceilândia")) {
            Cursos curso = new Cursos("Diurno", "Enfermagem", "-73.436", "-59.69", "-17.34", "-0.543", "8.181", "31.588");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Farmácia", "-24.717", "-55.339", "-89.04", "-34.385", "8.879", "26.623");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Fisioterapia", "null", "-27.192", "-46.347", "-5.34", "-11.698", "22.026");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Fonoaudiologia", "-49.59", "-19.55", "-28.932", "-35.718", "9.715", "12.128");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Saúde Coletiva", "null", "null", "-77.381", "-64.769", "-50.488", "-48.87");
            cursosList.add(curso);
            curso = new Cursos("Diurno", "Terapia Ocupacional", "-67.415", "-36.774", "-53.009", "-19.614", "-25.032", "-15.872");
            cursosList.add(curso);
            curso = null;
        }
        else if(campus.equalsIgnoreCase("Gama")) {
            Cursos curso = new Cursos("Diurno", "Engenharia", "-60.136", "-57.355", "-82.924", "-72.792", "44.741", "49.765");
            cursosList.add(curso);
            curso = null;
        }

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.simple_list_item_1, cursosList);
        ListView listView = (ListView) findViewById(R.id.cursos);
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
            TextView code;
            CheckBox name;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.simple_list_item_1, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Cursos curso = (Cursos) cb.getTag();
                        curso.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Cursos curso = cursosList.get(position);
            holder.name.setText(curso.getNomeCurso());
            holder.name.setChecked(curso.isSelected());
            holder.name.setTag(curso);

            return convertView;

        }
    }


}
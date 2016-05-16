package com.mikaelmello.argumentopas;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mikael on 02/03/2016.
 */
public class Cursos implements Serializable {
    protected String turno = null;
    protected String curso = null;
    protected String pppi = null;
    protected String pnppi = null;
    protected String ppi = null;
    protected String nppi = null;
    protected String negros = null;
    protected String universal = null;
    protected boolean isSelected = false;

    public Cursos(String turno, String curso, String pppi, String pnppi, String ppi, String nppi, String negros, String Universal){
        this.turno = turno;
        this.curso = curso;
        this.pppi = pppi;
        this.pnppi = pnppi;
        this.ppi = ppi;
        this.nppi = nppi;
        this.negros = negros;
        this.universal = Universal;
    }

    public String getNomeCurso(){
        return this.curso;
    }

    public ArrayList<String> getCursoTotal() {
        ArrayList<String> geral = new ArrayList<String>();
        geral.add(this.turno);
        geral.add(this.curso);
        geral.add(this.pppi);
        geral.add(this.pnppi);
        geral.add(this.ppi);
        geral.add(this.nppi);
        geral.add(this.negros);
        geral.add(this.universal);
        return geral;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(Boolean selection){
        isSelected = selection;
    }
}

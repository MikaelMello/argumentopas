package com.mikaelmello.argumentopas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMenuPrincipal extends AppCompatActivity {

    public Button buttonAjuda;
    public Button buttonCalcular;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu_principal);

        buttonAjuda = (Button)findViewById(R.id.ajuda);
        buttonCalcular = (Button)findViewById(R.id.calcular);

        buttonAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ActivityMenuPrincipal.this, ActivityAjuda.class);
                startActivity(intent);
            }
        });
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ActivityMenuPrincipal.this, ActivityMenu.class);
                startActivity(intent);
            }
        });
    }

}

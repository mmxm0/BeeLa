package br.ufrpe.beela.lugar.gui;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ufrpe.beela.gui.R;

/**
 * Created by max on 06/01/18.
 */

public class LugaresAct extends AppCompatActivity {

    private Button btIr;
    private double origemlatitude ;
    private double origemlongitude ;

    private double destinolatitude;
    private double destinolongitude ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_sugestao);

    btIr = findViewById(R.id.buttonIr);

    btIr.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chamarMapa();
        }
    });




    }

    private void chamarMapa() {
        origemlatitude = -8.189302;
        origemlongitude = -34.955261;

        destinolatitude = -8.014121;
        destinolongitude = -34.951131;

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + origemlatitude + "," + origemlongitude + "&daddr=" + destinolatitude + "," + destinolongitude));


            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));

            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Verifique se o Google Maps está instalado em seu dispositivo", Toast.LENGTH_SHORT).show();
        }
    }








}
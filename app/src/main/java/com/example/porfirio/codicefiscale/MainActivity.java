package com.example.porfirio.codicefiscale;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.porfirio.codicefiscale.engine.CitiesCodes;
import com.example.porfirio.codicefiscale.engine.Engine;
import com.example.porfirio.codicefiscale.engine.Person;
import com.example.porfirio.codicefiscale.engine.ReverseGeocoding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends Activity {
    CitiesCodes cc;
    Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        cc = new CitiesCodes();

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or use LocationManager.GPS_PROVIDER
        Location lastKnownLocation;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            this.requestPermissions( new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET}, 1 );
        }
        lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        String lat=Double.toString(lastKnownLocation.getLatitude());
        String lon=Double.toString(lastKnownLocation.getLongitude());
        String citta= ReverseGeocoding.getCity(lat,lon);

        final Spinner editLuogo = (Spinner) findViewById(R.id.luogoDiNascita);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item);
        adapter.addAll(CitiesCodes.cities);
        editLuogo.setAdapter(adapter);

        for (int i = 0; i < editLuogo.getCount(); i++) {
            if (adapter.getItem(i).equals(citta)) {
                editLuogo.setSelection(i);
            }
        }

        editLuogo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                person.setBornCity(CitiesCodes.cities.get(pos));
                return;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final EditText editNome = (EditText) findViewById(R.id.txtNome);
        final EditText editCognome = (EditText) findViewById(R.id.txtCognome);
        final TextView txtCodice = (TextView) findViewById(R.id.txtCodice);

        //TODO : Leggi l'oggetto sesso
        final EditText btnDataDiNascita = (EditText) findViewById(R.id.dataDiNascita);

        final DatePickerDialog dpd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                person.setDay(Integer.toString(dayOfMonth));
                person.setMonth(Integer.toString(month+1));
                person.setYear(Integer.toString(year));
                btnDataDiNascita.setText(Integer.toString(dayOfMonth)+"/"+Integer.toString(month)+"/"+Integer.toString(year));
                return;
            }
        },1980,1,1);

        btnDataDiNascita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: Apri dialog di scelta del tempo DatePickerDIalog
                dpd.show();
            };
        });


        final Spinner editSesso = (Spinner) findViewById(R.id.sesso);
        final ArrayAdapter<String> adapterSesso = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item);
        adapterSesso.add("M");
        adapterSesso.add("F");
        editSesso.setAdapter(adapterSesso);

        editSesso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                person.setSex(adapterSesso.getItem(pos));
                return;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnCalcola = (Button) findViewById(R.id.buttonCalcola);
        btnCalcola.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                person.setName(editNome.getText().toString());
                person.setSurname(editCognome.getText().toString());

                Engine engine=null;
                try {
                    engine=new Engine(person);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                txtCodice.setText(engine.getCode());

                return;
            }
        });



    }

}

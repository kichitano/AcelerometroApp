package com.example.aepis01.sensorapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    ConstraintLayout fondo;
    Button activador;
    Sensor s;
    SensorManager sensorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = (ConstraintLayout)findViewById(R.id.fondo);
        activador = (Button)findViewById(R.id.btn_sensor);

        activador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorM = (SensorManager)getSystemService(SENSOR_SERVICE);
                s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                sensorM.registerListener(MainActivity.this,s,SensorManager.SENSOR_DELAY_NORMAL);
                activador.setText("Activado");
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float valor=Float.parseFloat(String.valueOf(sensorEvent.values[0]));

        if (valor <= 2){
            Toast.makeText(this, String.valueOf(valor),Toast.LENGTH_SHORT).show();
            fondo.setBackgroundColor(Color.BLACK);}
        else{
            Toast.makeText(this, String.valueOf(valor),Toast.LENGTH_SHORT).show();
            fondo.setBackgroundColor(Color.WHITE);}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorM.unregisterListener(this,sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY));
    }
}

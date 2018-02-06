package com.udemy.wilfredo.cityworld10.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.udemy.wilfredo.cityworld10.R;

public class CityWorldActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAddCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_world);

        fabAddCity = findViewById(R.id.FABAddCity);
        fabAddCity.setOnClickListener(this);
    }


    /**
     * Se crea método para la acción onclick del botón agregar ciudad.
     * La única funcionalidad es redirigir al activity de agregar una nueva ciudad
     * @param view parámetro que representa la vista del activity principal
     */
    @Override
    public void onClick(View view) {
        Intent intentAddCity = new Intent(CityWorldActivity.this,AddEditCityActivity.class);
        startActivity(intentAddCity);
    }
}

package com.udemy.wilfredo.cityworld10.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udemy.wilfredo.cityworld10.R;
import com.udemy.wilfredo.cityworld10.model.City;

import io.realm.Realm;

public class AddEditCityActivity extends AppCompatActivity {

    Realm realm;

    private ImageView imageViewPreview;
    private EditText editTextCityName;
    private EditText editTextCityImage;
    private Button buttonPreview;
    private EditText editTextCityDescription;
    private RatingBar ratingBarCity;
    private FloatingActionButton fabSaveCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_city);

        //Se inicializan los objetos de la UI
        initUI();

        //Se agrega la acción de onClick al botón de previsualización
        buttonPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = editTextCityImage.getText().toString();
                if(link.length() > 0 && link.startsWith("http")) {
                    loadImageLinkForPreview(link);
                } else {
                    Toast.makeText(AddEditCityActivity.this,"Please enter a valid link",Toast.LENGTH_LONG).show();
                }
            }
        });

        fabSaveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewOrEditCity();
            }
        });
    }

    /**
     * Método que se encarga de cargar una imagen desde Internet.
     * Es necesario colocar el permiso de acceso a internet en el manifest
     * @param link parámetro que representa el link ingresado por el usuario
     */
    private void loadImageLinkForPreview(String link) {
        Picasso.with(this).load(link).fit().into(imageViewPreview);
    }

    /**
     * Método que se encarga de inicializar los elementos de la UI
     */
    private void initUI(){
        imageViewPreview = findViewById(R.id.imageViewPreview);
        editTextCityName = findViewById(R.id.editTextCityName);
        editTextCityImage = findViewById(R.id.editTextCityImage);
        buttonPreview = findViewById(R.id.buttonPreview);
        editTextCityDescription = findViewById(R.id.editTextCityDescription);
        ratingBarCity = findViewById(R.id.ratingBarCity);
        fabSaveCity = findViewById(R.id.FABSaveCity);
    }

    /**
     * Método encargado de agregar o editar una ciudad
     */
    private void addNewOrEditCity() {
        if(validateFields()){
            String nameCity = editTextCityName.getText().toString();
            String imageCity = editTextCityImage.getText().toString();
            String descriptionCity = editTextCityDescription.getText().toString();
            float numberStarsCity = ratingBarCity.getRating();

            //Se obtiene un ejemplar de la base de datos
            realm = Realm.getDefaultInstance();
            try {
                City addEditCity = new City(nameCity, descriptionCity, imageCity, numberStarsCity);
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(addEditCity);
                realm.commitTransaction();
            } finally {
                realm.close();
            }
        } else {
            Toast.makeText(this, "Must complete all fields", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método que valida el ingreso de los campos requeridos por el programa
     * @return boolean
     */
    private boolean validateFields() {

        boolean areValidFields = null != editTextCityName.getText()
                && editTextCityName.getText().toString().trim().length() > 0;
        if(areValidFields) {
            areValidFields = null != editTextCityDescription.getText()
                    && editTextCityDescription.getText().toString().trim().length() > 0;
            if(areValidFields) {
                areValidFields = null != editTextCityImage.getText()
                        && editTextCityImage.getText().toString().trim().length() > 0;
            }
        }
        return areValidFields;
    }

}

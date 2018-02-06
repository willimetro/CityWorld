package com.udemy.wilfredo.cityworld10.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udemy.wilfredo.cityworld10.R;

public class AddEditCityActivity extends AppCompatActivity {

    private EditText editTextCityImage;
    private ImageView imageViewPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_city);

        //Se inicializan los objetos de la UI
        editTextCityImage = findViewById(R.id.editTextCityImage);
        imageViewPreview = findViewById(R.id.imageViewPreview);
        Button buttonPreview = findViewById(R.id.buttonPreview);

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
    }

    /**
     * Método que se encarga de cargar una imagen desde Internet.
     * Es necesario colocar el permiso de acceso a internet en el manifest
     * @param link parámetro que representa el link ingresado por el usuario
     */
    private void loadImageLinkForPreview(String link) {
        Picasso.with(this).load(link).fit().into(imageViewPreview);
    }

}

package com.udemy.wilfredo.cityworld10.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.udemy.wilfredo.cityworld10.R;
import com.udemy.wilfredo.cityworld10.adapters.CityWorldAdapter;
import com.udemy.wilfredo.cityworld10.model.City;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class CityWorldActivity extends AppCompatActivity implements View.OnClickListener, RealmChangeListener<RealmResults<City>> {

    private FloatingActionButton fabAddCity;
    private RecyclerView recyclerCities;
    private CityWorldAdapter cityWorldAdapter;
    private RealmResults<City> cities;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_world);
        //Se obtienen las ciudades ya guardadas desde la base de datos
        realm = Realm.getDefaultInstance();
        cities = realm.where(City.class).findAll();
        //Se crea el adapatador para el RecyclerView
        cityWorldAdapter = new CityWorldAdapter(R.layout.recycler_cardview_item, cities, new CityWorldAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(City city, int position) {
                Intent editIntent = new Intent(CityWorldActivity.this,AddEditCityActivity.class);
                editIntent.putExtra("idCity", cities.get(position).getId());
                startActivity(editIntent);
            }
        }, new CityWorldAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(City city, int position) {
                showAlertForRemovingCity("Are you sure you want to delete " + city.getName() + "?",position);
            }
        });

        initUI();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerCities.setLayoutManager(mLayoutManager);
        recyclerCities.setAdapter(cityWorldAdapter);
        recyclerCities.setHasFixedSize(true);
        recyclerCities.setItemAnimator(new DefaultItemAnimator());
        recyclerCities.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    fabAddCity.hide();
                } else if(dy < 0){
                    fabAddCity.show();
                }
            }
        });
        cities.addChangeListener(this);
        fabAddCity.setOnClickListener(this);
    }

    /**
     * Método que inicializa los elementos de la UI
     */
    private void initUI(){
        recyclerCities = findViewById(R.id.rvCityWorld);
        fabAddCity = findViewById(R.id.FABAddCity);
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

    /**
     * Método que permite la actualización de la pantalla cuando exista un cambio en la base de datos
     * @param cities propiedad que representa el listado de ciudades
     */
    @Override
    public void onChange(@NonNull RealmResults<City> cities) {
        cityWorldAdapter.notifyDataSetChanged();
    }

    /**
     *Método que borra una ciudad desde la base de datos
     * @param position variable que indica la posicion en la lista de la ciudad
     */
    private void deleteCity(int position) {
        realm.beginTransaction();
        cities.get(position).deleteFromRealm();
        realm.commitTransaction();
    }

    /**
     * Método que muestra un alert de confirmación para el borrado de la ciudad
     * @param message mensaje del alert
     * @param position posicion del listado de cardviews
     */
    private void showAlertForRemovingCity(String message, final int position) {

        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage(message)
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteCity(position);
                        Toast.makeText(CityWorldActivity.this, "It has been deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }
}

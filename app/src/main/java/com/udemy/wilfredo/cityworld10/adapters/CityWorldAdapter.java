package com.udemy.wilfredo.cityworld10.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.udemy.wilfredo.cityworld10.R;
import com.udemy.wilfredo.cityworld10.model.City;

import java.util.List;

/**
 * Clase adaptador que permite superponer un cardview sobre un RecyclerView
 * Created by wilfredo on 07-02-18.
 */

public class CityWorldAdapter extends RecyclerView.Adapter<CityWorldAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<City> cities;
    private OnItemClickListener onItemClickListener;
    private OnButtonClickListener onButtonClickListener;

    public CityWorldAdapter(int layout, List<City> cities,
                            OnItemClickListener onItemClickListener,
                            OnButtonClickListener onButtonClickListener) {
        this.layout = layout;
        this.cities = cities;
        this.onItemClickListener = onItemClickListener;
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cities.get(position),onItemClickListener,onButtonClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameCity;
        public TextView descriptionCity;
        public TextView starsCity;
        public ImageView imageCity;
        public Button btnDeleteCity;

        public ViewHolder(View itemView) {
            super(itemView);
            nameCity = itemView.findViewById(R.id.textViewCityName);
            descriptionCity = itemView.findViewById(R.id.textViewCityDescription);
            starsCity = itemView.findViewById(R.id.textViewStars);
            imageCity = itemView.findViewById(R.id.imageViewCity);
            btnDeleteCity = itemView.findViewById(R.id.buttonDeleteCity);
        }

        public void bind(City city, OnItemClickListener onItemClickListener,
                         OnButtonClickListener onButtonClickListener){

        }
    }

    public interface OnItemClickListener {
        void onItemClick(City city, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(City city, int position);
    }
}

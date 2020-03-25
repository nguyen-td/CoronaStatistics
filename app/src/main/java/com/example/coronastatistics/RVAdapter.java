package com.example.coronastatistics;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
    List<CountryData> countryData;

    public RVAdapter(List<CountryData> countryData) {
        this.countryData = countryData;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_view, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int i) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String str_confirmed = "Confirmed: " + String.valueOf(formatter.format(countryData.get(i).getConfirmed()));
        String str_death = "Deaths: " + String.valueOf(formatter.format(countryData.get(i).getDeaths()));
        String str_recovered = "Recovered: " + String.valueOf(formatter.format(countryData.get(i).getRecovered()));

        // If there is no province, display country instead
        if(countryData.get(i).getProvince().equals("")) {
            holder.province.setText(countryData.get(i).getCountry());
        } else {
            holder.province.setText(countryData.get(i).getProvince());
        }

        holder.confirmed.setText(str_confirmed);
        holder.death.setText(str_death);
        holder.recovered.setText(str_recovered);
        holder.lastUpdated.setText(countryData.get(i).getLastUpdate());
    }

    @Override
    public int getItemCount() {
        // Log.e("Count", countryData.get(0).g);
        return countryData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView province;
        TextView confirmed;
        TextView death;
        TextView recovered;
        TextView lastUpdated;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            province = (TextView)itemView.findViewById(R.id.province);
            confirmed = (TextView)itemView.findViewById(R.id.confirmed);
            death = (TextView)itemView.findViewById(R.id.death);
            recovered = (TextView)itemView.findViewById(R.id.recovered);
            lastUpdated = (TextView)itemView.findViewById(R.id.lastUpdated);
        }
    }

}

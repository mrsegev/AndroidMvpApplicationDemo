package com.yossisegev.androidmvpapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yossisegev.androidmvpapplication.R;
import com.yossisegev.androidmvpapplication.models.WeatherStatus;

import java.util.List;


/**
 * Created by Yossi Segev
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    List<WeatherStatus> mItems;

    public WeatherAdapter(List<WeatherStatus> items) {
        mItems = items;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_row, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        WeatherStatus weatherStatus = mItems.get(position);

        // Name
        holder.name.setText(weatherStatus.getName());

        // Temp
        String tempStr = String.valueOf(weatherStatus.getMainData().getTemp());
        holder.temp.setText(tempStr);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView temp;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            temp = (TextView) itemView.findViewById(R.id.temp);
        }
    }
}

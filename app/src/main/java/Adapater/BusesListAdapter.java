package Adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiticket.retrofixe.BusDetails;
import com.example.mobiticket.retrofixe.R;

import java.util.ArrayList;
import java.util.Arrays;

import Modes.Bus;
import Modes.Price;
import Modes.Seats;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class BusesListAdapter extends RecyclerView.Adapter<BusesListAdapter.viewHolder> {
    private ArrayList<Bus> buses;
    private Seats[] seats;
    private Context context;

    public BusesListAdapter(ArrayList<Bus> buses, Context context) {
        this.buses = buses;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buses_list_custom_layout,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, final int position) {
        final Bus bus = buses.get(position);
        int total_seats = Integer.parseInt(bus.getTotal_seats());
        String[] departure_time_array = bus.getDeparture_time().split(",");
        String dept_date = departure_time_array[0];
        String dept_time = departure_time_array[1];
        String[] arrival_time_array = bus.getDeparture_time().split(",");
        String arrival_date = arrival_time_array[0];
        String arrival_time = arrival_time_array[1];

//        Log.d("Location", dept_date);

        viewHolder.m_bus_name.setText(bus.getRoute());
        viewHolder.m_seat_remaining.setText(bus.getSeats_available()+ " Available");
        viewHolder.m_dept_town.setText(bus.getFrom());
        viewHolder.m_arrival_town.setText(bus.getTo());
        viewHolder.m_dept_time.setText(dept_time);
        viewHolder.m_arrival_time.setText(arrival_time);

//        gets price array
        ArrayList<Price> priceArrayList = new ArrayList<>(Arrays.asList(bus.getPrice()));
        Price price = priceArrayList.get(0);
        viewHolder.m_fare.setText("Kshs " + price.getName().split("-")[2]);
//        check type of bus and load respective image
        if(total_seats > 11){
            viewHolder.bus_image.setImageResource(R.drawable.bus);
        }else if(total_seats <= 11) {
            viewHolder.bus_image.setImageResource(R.drawable.shuttle);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Seats> seatsArrayList = new ArrayList<>(Arrays.asList(bus.getSeats()));
                Log.d("Location", String.valueOf(seatsArrayList.get(0)));
                Seats current_seat = seatsArrayList.get(0);


                Toast.makeText(context, "Card clicked"+bus.getRoute(), Toast.LENGTH_LONG).show();
                Intent busDetails = new Intent(context, BusDetails.class);
                busDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                busDetails.putExtra("bus_name",bus.getRoute());
                busDetails.putExtra("seats_available", current_seat.getName());
                busDetails.putExtra("seater", current_seat.getSeater());
                busDetails.putExtra("id", current_seat.getId());
                context.startActivity(busDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(buses != null && buses.size() != 0);
        return buses.size();
    }

    public  static class  viewHolder extends RecyclerView.ViewHolder {
        private TextView m_bus_name, m_seat_remaining, m_fare, m_dept_time,
                        m_dept_town, m_time_diff, m_arrival_time, m_arrival_town;
        private ImageView bus_image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            bus_image = itemView.findViewById(R.id.imv_bus);
            m_bus_name = itemView.findViewById(R.id.tv_bus_name);
            m_seat_remaining  = itemView.findViewById(R.id.tv_seats_remaining);
            m_fare = itemView.findViewById(R.id.tv_fare);
            m_dept_time = itemView.findViewById(R.id.tv_dept_time);
            m_dept_town = itemView.findViewById(R.id.tv_dept_town);
            m_time_diff = itemView.findViewById(R.id.tv_time_diff);
            m_arrival_time = itemView.findViewById(R.id.tv_arrival_time);
            m_arrival_town = itemView.findViewById(R.id.tv_arrival_town);


        }
    }
}

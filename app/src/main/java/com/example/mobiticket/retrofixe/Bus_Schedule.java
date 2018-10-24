package com.example.mobiticket.retrofixe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import Adapater.BusesListAdapter;
import Endpoints.EndPointService;
import Modes.Bus;
import Modes.Post;
import Modes.RequestPojo.getAllBuses;
import Modes.SearchBuses;
import Modes.Seats;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Constants.constants.API_KEY;
import static Constants.constants.HASH;
import static Constants.constants.USERNAME;

public class Bus_Schedule extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EndPointService endPointService;
    private ArrayList<Bus> buses_list;
    private RecyclerView bus_recycler_view;
    private BusesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__schedule);
        endPointService = RetrofitClientInstance.getRetrofitInstance().create(EndPointService.class);
        initializeView();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadBusList("2","5","22-10-2018");
//                savePost("Vgaggag", "thoa aiiaiiaia", 2663333);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initializeView(){
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        bus_recycler_view = findViewById(R.id.bus_list_recycler);
        bus_recycler_view.setHasFixedSize(true);
        bus_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        bus_recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

//        bus_recycler_view.addOnItemTouchListener();


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Nairobi - Eldoret");

        }

    }
    private  void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    private  void loadBusList(String from, String to , String travel_date){
        getAllBuses allBuses = new getAllBuses(USERNAME, API_KEY,"SearchSchedule",from,to,travel_date,HASH);
        endPointService.getBusSchedule(allBuses).enqueue(new Callback<SearchBuses>() {
            @Override
            public void onResponse(Call<SearchBuses> call, Response<SearchBuses> response) {
                if(response.isSuccessful()){
                    showToast("Successful Response");
                    SearchBuses searchBuses = response.body();
                    Log.d("Location",searchBuses.getResponse_code());
                    Log.d("Location",searchBuses.getResponse_message());

                    buses_list = new ArrayList<>(Arrays.asList(searchBuses.getBus()));

                    adapter = new BusesListAdapter(buses_list, getApplicationContext());
                    adapter.notifyDataSetChanged();
                    bus_recycler_view.setAdapter(adapter);
                    for (Bus bus : buses_list){
                        ArrayList<Seats> seatsArrayList = new ArrayList<>(Arrays.asList(bus.getSeats()));
                        for (Seats seat: seatsArrayList){
                            Log.d("Location", seat.getName());
                        }

                    }

//                    Log.d("Location",searchBuses.getResponse_message());


                }else {
                    showToast("Failed Response");
                    Log.d("Location",response.toString());
                    Log.d("Location",call.request().url().toString());
                    Log.d("Location",call.request().headers().toString());
                    Log.d("Location",""+call.request().isHttps());

                }
            }

            @Override
            public void onFailure(Call<SearchBuses> call, Throwable t) {
                showToast("OnFailure called"+t.getMessage());

            }
        });

    }

}

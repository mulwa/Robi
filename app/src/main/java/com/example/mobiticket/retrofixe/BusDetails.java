package com.example.mobiticket.retrofixe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class BusDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private String seats_array;
    private String seater;
    private String id;
    private  String bus_name;
    private TextView m_seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        initializeView();

        Intent intent = getIntent();
        if(intent != null){
            bus_name = intent.getStringExtra("bus_name");
            seater = intent.getStringExtra("seater");
            seats_array = intent.getStringExtra("seats_available");
            id = intent.getStringExtra("id");
            m_seats.setText(seats_array);
//            sets toolbar name
            getSupportActionBar().setTitle(bus_name);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        m_seats = findViewById(R.id.tv_seats_array);

    }
}

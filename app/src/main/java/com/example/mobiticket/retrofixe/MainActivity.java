package com.example.mobiticket.retrofixe;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Endpoints.EndPointService;
import Modes.Cities;
import Modes.Location;
import Modes.PhotoModel;
import Modes.Post;
import Modes.RequestPojo.LocationRequest;
import Network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Constants.constants.API_KEY;
import static Constants.constants.USERNAME;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView m_to, m_from, m_date;
    private Button btn_search;
    private  EndPointService endPointService;
    private  String[] locations  = {"Nairobi","Mombasa", "Eldoret", "Kakamega", "Juja" };
    private Calendar calendar;
    private ArrayList<Cities> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendar = Calendar.getInstance();
        endPointService = RetrofitClientInstance.getRetrofitInstance().create(EndPointService.class);

        initializeViews();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Buttom clicked");
//                getLocation();
                launchBusSchedule();

            }
        });
//        load destination prompt select
        m_to.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenAlert("Travelling To", m_to);

            }
        });
//        launch from location selection
        m_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAlert("Travelling From", m_from);

            }
        });
        m_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("Load date Picket");
                showDatePicker();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initializeViews(){
        m_date = findViewById(R.id.ed_date);
        m_from = findViewById(R.id.ed_from);
        m_to = findViewById(R.id.ed_to);
        btn_search = findViewById(R.id.btn_search);
    }
    private  void launchBusSchedule(){
        startActivity(new Intent(getApplicationContext(), Bus_Schedule.class));
    }
    private void getLocation(){
        endPointService.getLocation(new LocationRequest(USERNAME,API_KEY,"AvailableCities")).enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if(response.isSuccessful()){
                    showToast("On Sucess Called"+response);//
                    Log.d("Location",response.body().toString());
                    Location model = response.body();
                    Log.d("Location",model.getResponse_code());
//                    Cities[] city = model.getCities();

                    cities = new ArrayList<>(Arrays.asList(model.getCities()));

                    for(Cities city : cities){
                        Log.d("Location",  city.getId() +" " + city.getName());
                    }

                    Log.d("Location",String.valueOf(model.getResponse_message()));


                 }else {
                    showToast("Response not successfull"+response);
                    Log.d("Location",response.toString());
                    Log.d("Location",call.request().url().toString());
                    Log.d("Location",call.request().headers().toString());
                    Log.d("Location",""+call.request().isHttps());
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                showToast("On failure Called"+t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private  void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    private void OpenAlert(String title, final TextView editText){
        AlertDialog.Builder  mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(title);
        mBuilder.setSingleChoiceItems(locations, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                editText.setText(locations[position]);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
    private  void  showDatePicker(){
        new DatePickerDialog(this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            m_date.setText(""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

        }
    };

}

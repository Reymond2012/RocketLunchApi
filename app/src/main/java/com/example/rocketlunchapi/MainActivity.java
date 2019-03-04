package com.example.rocketlunchapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rocketlunchapi.home.HomeContract;
import com.example.rocketlunchapi.home.HomePresenter;
import com.example.rocketlunchapi.model.Launch;
import com.example.rocketlunchapi.model.LunchAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeContract.View {

    EditText etDay, etMonth, etYear;
    Button btDisplay;

    LunchAdapter lunchAdapter = new LunchAdapter();
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDay = findViewById(R.id.etDay);
        etMonth = findViewById(R.id.etMonth);
        etYear = findViewById(R.id.etYear);

        btDisplay = findViewById(R.id.btDisplay);


        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(lunchAdapter);
        presenter = new HomePresenter(this);

        btDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int yearNum = Integer.parseInt(etYear.getText().toString());
                String year = String.format("%04d",yearNum);
                int monthNum = Integer.parseInt(etMonth.getText().toString());
                String month = String.format("%02d",monthNum);
                int dayNum = Integer.parseInt(etDay.getText().toString());
                String day =  String.format("%02d",dayNum);
                final String date = year+"-"+month+"-"+day;

                if((Boolean) isDateValid(date)){
                    presenter.getLaunchDate(date);
                }else {
                    Toast.makeText(getApplicationContext(),"Enter right Date", Toast.LENGTH_LONG ).show();
                }

            }
        });

    }

    @Override
    public void showLaunches(List<Launch> result) {
        lunchAdapter.setData(result);

    }

    @Override
    public void showError(String Message) {

        Toast.makeText(this,Message, Toast.LENGTH_LONG).show();

    }

    private boolean isDateValid(String date){
        if(date == null){
            return false;
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {

            Date d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}



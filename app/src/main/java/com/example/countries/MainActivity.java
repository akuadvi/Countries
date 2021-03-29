package com.example.countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Database db;
    RecyclerView recyclerView;
    Adaptor adaptor;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but=findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.dao().delete();            }
        });


        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=Room.databaseBuilder(getApplicationContext(),Database.class,"countries").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        final GetService apiService = RetrofitClient.getRetrofitInstance().create(GetService.class);
        Call<List<countries>> call = apiService.getCountry();
        call.enqueue(new Callback<List<countries>>() {
            @Override
            public void onResponse(Call<List<countries>> call, Response<List<countries>> response) {
                for (countries c:response.body())
                {

                    db.dao().add(c);
                }

            }

            @Override
            public void onFailure(Call<List<countries>> call, Throwable t) {
                Log.e("onFailure", t.toString());


            }
        });

        List<countries> test=db.dao().get();
        adaptor=new Adaptor(this,test);
        recyclerView.setAdapter(adaptor);


        for(countries c:test)
            Log.i("akul",c.getFlag());
    }
}
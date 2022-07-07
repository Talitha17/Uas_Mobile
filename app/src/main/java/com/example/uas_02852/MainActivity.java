package com.example.uas_02852;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.uas_02852.Adapter.BarangAdapter;
import com.example.uas_02852.Model.BarangModel;
import com.example.uas_02852.Utill.DataApi;
import com.example.uas_02852.Utill.InterfaceBarang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager layoutManager;
    private BarangAdapter barangAdapter;
    private List<BarangModel> barangModelList;
    private InterfaceBarang interfaceBarang;
    Button btn_insert, btn_exit;

    androidx.recyclerview.widget.RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        btn_insert = findViewById(R.id.btn_insert);
        btn_exit = findViewById(R.id.btn_exit);

        recyclerView    =   findViewById(R.id.rcylrBarang);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        interfaceBarang= DataApi.getClient().create (InterfaceBarang.class);

        btn_insert.setOnClickListener(View -> {
            Intent intent = new Intent(MainActivity.this, TambahBarang.class);
            startActivity(intent);
        });

        btn_exit.setOnClickListener(View -> {
            finish();
        });



        tampilkanData();


    }

    private void tampilkanData() {
        Call<List<BarangModel>> call = interfaceBarang.getBarang();

        call.enqueue(new Callback<List<BarangModel>>() {

            @Override
            public void onResponse(Call<List<BarangModel>> call, Response<List<BarangModel>> response) {

                barangModelList = response.body();
                barangAdapter= new BarangAdapter(MainActivity.this, barangModelList);
                recyclerView.setAdapter(barangAdapter);
            }


            @Override
            public void onFailure(Call<List<BarangModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(MainActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();


//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }





    public void scanner(View view) {
        startActivity(new Intent(MainActivity.this, TambahBarang.class));
    }


}


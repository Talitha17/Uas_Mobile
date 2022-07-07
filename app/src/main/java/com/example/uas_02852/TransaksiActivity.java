package com.example.uas_02852;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.uas_02852.Adapter.AdapterTransaksi;
import com.example.uas_02852.Model.TransaksiModel;
import com.example.uas_02852.Utill.DataApi;
import com.example.uas_02852.Utill.InterfaceTransaksi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity  {

    private RecyclerView.LayoutManager layoutManager;
    private AdapterTransaksi adapterTrans;
    private List<TransaksiModel> transaksiModelList;
    private InterfaceTransaksi interfaceTransaksi;
    RecyclerView recyclerView;
    Button fabAdd, btn_insert,btn_Back;
    private ImageButton btnBack;
    private ShimmerRecyclerView mShimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        // Method hidenavbar

        hideNavbar();

        // Method initilize

        initilize();

        // Mengatur warna tint fab





        setmShimmerRecyclerView();


        interfaceTransaksi = DataApi.getClient().create(InterfaceTransaksi.class);
        tampilkanData();


        btn_insert.setOnClickListener(view -> {
            startActivity(new Intent(TransaksiActivity.this, MediaBarcode.class));
        });

        btn_Back = (Button) findViewById(R.id.btn_back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransaksiActivity.this,DashboardActivity.class));
            }
        });
    }


    private void refreshItem() {
        tampilkanData();
    }

    // Method untuk memanggi data json

    private void tampilkanData() {
        Call<List<TransaksiModel>> call = interfaceTransaksi.getBarang2();

        call.enqueue(new Callback<List<TransaksiModel>>() {

            @Override
            public void onResponse(Call<List<TransaksiModel>> call, Response<List<TransaksiModel>> response) {

                transaksiModelList = response.body();
                adapterTrans = new AdapterTransaksi(TransaksiActivity.this, transaksiModelList);
                mShimmerRecyclerView.setAdapter(adapterTrans);

            }


            @Override
            public void onFailure(Call<List<TransaksiModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(TransaksiActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();


            }
        });
    }

    private void hideNavbar() {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void initilize() {
        btn_insert = findViewById(R.id.btn_add);
        mShimmerRecyclerView = findViewById(R.id.rcylrBarang2);
    }

    private void setmShimmerRecyclerView() {


        mShimmerRecyclerView.setAdapter(adapterTrans);

        mShimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this),
                R.layout.list_data_barang_transaksi);

        mShimmerRecyclerView.setItemViewType((type, position) -> {
            switch (type) {
                case ShimmerRecyclerView.LAYOUT_GRID:
                    return position % 2 == 0
                            ? R.layout.list_data_barang_transaksi
                            : R.layout.list_data_barang_transaksi;

                default:
                case ShimmerRecyclerView.LAYOUT_LIST:
                    return position == 0 || position % 2 == 0
                            ? R.layout.list_data_barang_transaksi
                            : R.layout.list_data_barang_transaksi;
            }
        });

        mShimmerRecyclerView.showShimmer();     // to start showing shimmer

        layoutManager = new LinearLayoutManager(this);
        mShimmerRecyclerView.setLayoutManager(layoutManager);
        mShimmerRecyclerView.setHasFixedSize(true);


    }


}
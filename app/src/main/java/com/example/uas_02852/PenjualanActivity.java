package com.example.uas_02852;
import com.example.uas_02852.Model.TransaksiModel;
import com.example.uas_02852.Utill.DataApi;
import com.example.uas_02852.Utill.InterfaceTransaksi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjualanActivity extends AppCompatActivity {

    EditText kd_brg;
    EditText nm_brg;
    EditText hrg_brg;
    EditText jml_brg;
    EditText satuan_brg;
    EditText jumlah_penjualan;
    Integer total, jumlahPenjualan, totalBarang;
    TextView tv_total, tvTotal2;
    String kode_brg, nama_brg, harga_brg, jumlah_brg, satuan_barg, tanggal, waktu, jmlPenjualan, stokBarang;
    String stokFinal;

    Button save;

    InterfaceTransaksi interfaceTransaksi;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refPenjualan = database.getReference("Penjualan");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);

        initilize();

        // Mengambil data menggunakan intent

        Intent intent = getIntent();
        kd_brg.setText(intent.getStringExtra("kd_brg2"));
        nm_brg.setText(intent.getStringExtra("nm_brg2"));
        hrg_brg.setText(intent.getStringExtra("hrg_brg2"));
        jml_brg.setText(intent.getStringExtra("jml_brg2"));
        satuan_brg.setText(intent.getStringExtra("satuan_brg2"));



        getDateTime();

        // Disable Edittext


        disableEdittext();
        // Set value ke edittexxt

        kode_brg = kd_brg.getText().toString();
        nama_brg = nm_brg.getText().toString();
        harga_brg = hrg_brg.getText().toString();
        jumlah_brg = jml_brg.getText().toString();
        satuan_barg = satuan_brg.getText().toString();
        jmlPenjualan = jumlah_penjualan.getText().toString();

        tv_total = findViewById(R.id.tv_total);




        // Menghitung total harga
        jumlah_penjualan.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                // Jika edittetxt fokus
                if (hasFocus){
                    jml_brg.setText(intent.getStringExtra("jml_brg2"));

                } else {

                    if (jumlah_penjualan.getText().toString().equals("")){
                        total = 0;
                        totalBarang = 0;
                    }


                    else {

                        jumlahPenjualan = Integer.parseInt(jumlah_penjualan.getText().toString());
                        totalBarang = Integer.parseInt(jml_brg.getText().toString())  - jumlahPenjualan;
                        total = jumlahPenjualan * Integer.parseInt(hrg_brg.getText().toString());

                        if(Integer.parseInt(jml_brg.getText().toString()) < jumlahPenjualan )  {
                            Toast.makeText(PenjualanActivity.this, "Jumlah barang melebihi stok", Toast.LENGTH_LONG).show();
                            jumlah_penjualan.setText("Stok tidak mencukupi");
                            jumlah_penjualan.setError("Jumlah barang melebihi stok");
                            jml_brg.setText(intent.getStringExtra("jml_brg2"));
                            save.setClickable(false);
                            tv_total.setText("Jumlah melebihi stok");
                            tvTotal2.setVisibility(View.GONE);

                        }
                        else {
                            save.setClickable(true);
                            tv_total.setText(String.valueOf(total));
                            jml_brg.setText(String.valueOf(totalBarang));
                            stokBarang = jml_brg.getText().toString();
                            tvTotal2.setText(String.valueOf(totalBarang));
                            tvTotal2.setVisibility(View.GONE);
                            stokFinal = tvTotal2.getText().toString();

                        }



                    }

                }
            }
        });





    }





    private void disableEdittext() {
        kd_brg.setEnabled(false);
        nm_brg.setEnabled(false);
        hrg_brg.setEnabled(false);
        jml_brg.setEnabled(false);
        satuan_brg.setEnabled(false);
    }

    // Method Mengambil tanggal dan waktu saat ini

    private void getDateTime() {
        tanggal = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        waktu = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }

    private void initilize() {
        kd_brg = findViewById(R.id.inKdBrg);
        nm_brg = findViewById(R.id.inNmBrg);
        hrg_brg = findViewById(R.id.inHrgBrg);
        jml_brg = findViewById(R.id.inJumlahBrg);
        satuan_brg = findViewById(R.id.inSatuanBrg);
        save = findViewById(R.id.btnSave);
        tv_total = findViewById(R.id.tv_total);
        tvTotal2 =  findViewById(R.id.tv_total2);
        jumlah_penjualan = findViewById(R.id.inJumlahJual);
    }

    public void simpandata(View view) {
        DataApi.getClient().create(InterfaceTransaksi.class).simpanPenjualan(kode_brg, stokBarang).enqueue(new Callback<TransaksiModel>() {
            @Override
            public void onResponse(Call<TransaksiModel> call, Response<TransaksiModel> response) {
                if (response.isSuccessful()) {

                    // Memanggil method simpan ke firebase
                    simpanPenjualan(kode_brg, nama_brg, harga_brg, totalBarang, total, satuan_barg, tanggal, waktu);


                    Toast.makeText(PenjualanActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PenjualanActivity.this, TransaksiActivity.class));
                } else {
                    Toast.makeText(PenjualanActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<TransaksiModel> call, Throwable t) {
                Toast.makeText(PenjualanActivity.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Method simpan data ke dalam firebase

    private void simpanPenjualan(String kode_brg, String nama_brg, String harga_brg, Integer total, Integer totalPembelian, String satuan_barg,  String tanggal, String waktu) {
        String penjualan = refPenjualan.push().getKey();

        refPenjualan.child(penjualan).child("kode").setValue(kode_brg);
        refPenjualan.child(penjualan).child("nama").setValue(nama_brg);
        refPenjualan.child(penjualan).child("harga").setValue(harga_brg);
        refPenjualan.child(penjualan).child("Stok").setValue(total);
        refPenjualan.child(penjualan).child("Total Pembelian").setValue(totalPembelian);
        refPenjualan.child(penjualan).child("satuan").setValue(satuan_barg);
        refPenjualan.child(penjualan).child("tanggal").setValue(tanggal);
        refPenjualan.child(penjualan).child("waktu").setValue(waktu);
        Toast.makeText(PenjualanActivity.this, "Berhasil menambahkan data ke firebase", Toast.LENGTH_LONG).show();
    }
}
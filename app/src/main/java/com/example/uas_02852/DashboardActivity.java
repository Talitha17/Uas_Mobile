package com.example.uas_02852;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class DashboardActivity extends AppCompatActivity {

    CardView btn_product, btn_transaksi, btn_about, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initlize();
        onClick();
    }

    private void onClick() {
        btn_about.setOnClickListener(view -> {
            startActivity(new Intent(DashboardActivity.this, AboutActivity.class));
        });

        btn_transaksi.setOnClickListener(view -> {
            startActivity(new Intent(DashboardActivity.this, TransaksiActivity.class));
        });

        btn_exit.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
            builder.setTitle("Keluar Aplikasi");
            builder.setMessage("Apakah anda yakin ingin keluar dari aplikasi ini?");
            builder.setPositiveButton("Ya", (dialog, which) -> System.exit(1));
            builder.setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        btn_product.setOnClickListener(view -> {
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
        });
    }

    private void initlize() {
        btn_product = findViewById(R.id.data);
        btn_transaksi = findViewById(R.id.transaksi);
        btn_about = findViewById(R.id.about);
        btn_exit = findViewById(R.id.exit);
    }
}
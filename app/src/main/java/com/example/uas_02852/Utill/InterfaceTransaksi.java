package com.example.uas_02852.Utill;

import com.example.uas_02852.Model.TransaksiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceTransaksi {
    @GET("qrcode/")
    Call<List<TransaksiModel>> getBarang2();
    @FormUrlEncoded
    @POST("qrcode/transaksi.php")
    Call<TransaksiModel> simpanPenjualan(@Field("kode") String kode,
                                         @Field("jumlah") String jumlah);

    @DELETE("qrcode/")
    Call<TransaksiModel> deleteBarang(@Query("kode") String kode);
}

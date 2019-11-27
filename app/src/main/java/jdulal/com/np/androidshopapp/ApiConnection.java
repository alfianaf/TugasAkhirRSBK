package jdulal.com.np.androidshopapp;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiConnection {
    @Headers("Content-Type: application/json")

    @GET("Food")
    Call<JsonObject> getBarang();

    @GET("Food")
    Call<JsonObject> getBarangById(
            @Query("id") int id
    );

    @FormUrlEncoded
    @POST("Food")
    Call<JSONObject> addBarang(
            @Field("nama") String nama,
            @Field("harga") String harga,
            @Field("stok") String stok
    );

    @FormUrlEncoded
    @POST("Foods")
    Call<JSONObject> updateBarang(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("harga") String harga,
            @Field("stok") String stok
    );

    @FormUrlEncoded
    @POST("Foods")
    Call<JSONObject> deleteBarang(
            @Field("id") String id
    );
}

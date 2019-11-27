package jdulal.com.np.androidshopapp;

import android.util.Log;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPresenter {
    private AddView addView;

    public AddPresenter(AddView addView){
        this.addView = addView;
    }

    public void addAllBarang(){
        final String tag = "Add-addBarang";
        String nama = addView.getNama();
        String harga = addView.getHarga();
        String stok = addView.getStok();

        RetrofitClient.getInstance()
                .getApi()
                .addBarang(nama, harga, stok)
                .enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if(response.isSuccessful()){
                            addView.successAddBarang();
                            Log.e(tag, response.body().toString());
                        } else {
                            addView.failedAddBarang();
                            Log.e(tag, response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        addView.failedAddBarang();
                        Log.e(tag, t.getMessage());
                    }
                });
    }
}

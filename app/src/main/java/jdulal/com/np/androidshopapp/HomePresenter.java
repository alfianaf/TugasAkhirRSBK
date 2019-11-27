package jdulal.com.np.androidshopapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView homeView;

    public HomePresenter (HomeView homeView){
        this.homeView = homeView;
    }

    public void getAllBarang(){
        final String tag = "Home-getBarang";
        RetrofitClient.getInstance()
                .getApi()
                .getBarang()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()){
                            JsonObject body = response.body();
                            JsonArray array = body.get("data").getAsJsonArray();
                            Type type = new TypeToken<List<DataBarang>>(){}.getType();
                            List<DataBarang> dataBarangs = new Gson().fromJson(array, type);
                            homeView.successShowBarang(dataBarangs);
                            Log.e(tag, response.body().toString());
                        } else {
                            homeView.failedShowBarang("Maaf terjadi kesalahan");
                            Log.e(tag, response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        homeView.failedShowBarang("Maaf terjadi kesalahan");
                        Log.d(tag, t.getMessage());
                    }
                });
    }
}

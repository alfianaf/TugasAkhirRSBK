package jdulal.com.np.androidshopapp;

import java.util.List;

public interface HomeView {
    void successShowBarang(List<DataBarang> dataBarangs);
    void failedShowBarang(String message);
}

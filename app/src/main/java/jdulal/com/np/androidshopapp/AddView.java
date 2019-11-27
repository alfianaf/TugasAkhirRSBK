package jdulal.com.np.androidshopapp;

public interface AddView {
    String getNama();

    String getHarga();

    String getStok();

    void successAddBarang();

    void failedAddBarang();
}

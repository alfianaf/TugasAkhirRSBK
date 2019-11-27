package jdulal.com.np.androidshopapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangViewHolder> {
    private List<DataBarang> dataBarangs;
    private BarangListener barangListener;

    public BarangAdapter(List<DataBarang> dataBarangs){
        this.dataBarangs = dataBarangs;
    }

    public void setAdapterListener(BarangListener barangListener){
        this.barangListener = barangListener;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        DataBarang dataUser = get(position);
        holder.bind(dataUser, barangListener);
    }

    private DataBarang get(int position) {
        return dataBarangs.get(position);
    }

    @Override
    public int getItemCount() {
        if (dataBarangs == null) return 0;
        return dataBarangs.size();
    }
}
package jdulal.com.np.androidshopapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BarangViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    TextView tvNama, tvHarga, tvStok;

    public BarangViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        linearLayout = itemView.findViewById(R.id.linear_item);
        tvNama = itemView.findViewById(R.id.tvNama);
        tvHarga = itemView.findViewById(R.id.tvHarga);
        tvStok = itemView.findViewById(R.id.tvStok);
    }

    public void bind(final DataBarang dataBarang, final BarangListener barangListener) {
        tvNama.setText(dataBarang.getNama());
        tvHarga.setText(dataBarang.getHarga());
        tvStok.setText(dataBarang.getStok());

        linearLayout.setOnClickListener(v -> barangListener.onBarangClick(dataBarang));
    }
}
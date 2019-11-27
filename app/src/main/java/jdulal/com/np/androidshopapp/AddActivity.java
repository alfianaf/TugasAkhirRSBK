package jdulal.com.np.androidshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements AddView {

    private AddPresenter addPresenter;
    EditText etAddNama, etAddHarga, etAddStok;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initPresenter();
        initView();
        addBarang();
    }

    private void initView() {
        etAddNama = findViewById(R.id.etAddNama);
        etAddHarga = findViewById(R.id.etAddHarga);
        etAddStok = findViewById(R.id.etAddStok);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void initPresenter() {
        addPresenter = new AddPresenter(this);
    }

    private void addBarang() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPresenter.addAllBarang();
            }
        });
    }

    @Override
    public String getNama() {
        return etAddNama.getText().toString();
    }

    @Override
    public String getHarga() {
        return etAddHarga.getText().toString();
    }

    @Override
    public String getStok() {
        return etAddStok.getText().toString();
    }

    @Override
    public void successAddBarang() {
        Toast.makeText(this, "Berhasil Menambahkan Data Barang", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(AddActivity.this, HomeActivity.class);
        startActivity(home);
        finish();
    }

    @Override
    public void failedAddBarang() {
        Toast.makeText(this, "Gagal Menambah Data Barang", Toast.LENGTH_SHORT).show();
    }
}

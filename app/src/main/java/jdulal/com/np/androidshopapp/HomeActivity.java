package jdulal.com.np.androidshopapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView, BarangListener {

    private HomePresenter homePresenter;
    private BarangAdapter barangAdapter;
    FloatingActionButton fabHome;
    RecyclerView rvHome;
    SwipeRefreshLayout srlHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initPresenter();
        initView();
        initDataPresenter();

        addBarang();
        refresh();
    }

    private void initView() {
        fabHome = findViewById(R.id.fabHome);
        rvHome = findViewById(R.id.rvHome);
        srlHome = findViewById(R.id.srlHome);
    }

    private void initPresenter() {
        homePresenter = new HomePresenter((HomeView) this);
    }

    private void initDataPresenter() {
        homePresenter.getAllBarang();
    }

    private void addBarang() {
        fabHome.setOnClickListener(v -> {
            Intent addBarang = new Intent(HomeActivity.this, AddActivity.class);
            HomeActivity.this.startActivity(addBarang);
            HomeActivity.this.finish();
        });
    }

    private void refresh(){
        srlHome.setOnRefreshListener(HomeActivity.this::initDataPresenter);
    }

    @Override
    public void successShowBarang(List<DataBarang> dataBarangs) {
        if (srlHome.isRefreshing()){
            srlHome.setRefreshing(false);
        }
        barangAdapter = new BarangAdapter(dataBarangs);
        barangAdapter.setAdapterListener((BarangListener) this);
        rvHome.setLayoutManager(new LinearLayoutManager(this));
        rvHome.setAdapter(barangAdapter);
    }

    @Override
    public void failedShowBarang(String message) {
        Toast.makeText(this, "Maaf terjadi kesalahan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBarangClick(DataBarang dataBarang) {
        //Intent intent = new Intent(this, DetailActivity.class);
        //intent.putExtra(Constant.Extra.DATA, dataBarang);
        //startActivity(intent);
    }
}

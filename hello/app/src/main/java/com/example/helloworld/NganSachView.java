package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DATABASE.SQLiteHelper;
import adapter.NganSachAdapter;
import model.NganSach;

public class NganSachView extends AppCompatActivity implements NganSachAdapter.NganSachListener {

    private SQLiteHelper db;

    private TextView tongNganSach, luuDongText;

    private ImageView back, home, suaTongNS, addNS;

    private RecyclerView recyclerView;

    private NganSachAdapter nganSachAdapter;

    private List<NganSach> list;

    private float tongNS , luuDong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngan_sach_view);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NganSachView.this, MainActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NganSachView.this, MainActivity.class);
                startActivity(intent);
            }
        });

        suaTongNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NganSachView.this, SuaTongNganSach.class);
                startActivity(intent);
            }
        });

        addNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NganSachView.this,ThemNganSach.class);
                startActivity(intent);
            }
        });

        tongNganSach.setText(String.valueOf(tongNS));
        luuDongText.setText(String.valueOf(luuDong));

        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(nganSachAdapter);
        nganSachAdapter.setNganSachListener(this);
    }

    private void initView(){
        db = new SQLiteHelper(this);
        tongNganSach = findViewById(R.id.tongNganSach);
        luuDongText = findViewById(R.id.luudong);
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton);
        suaTongNS = findViewById(R.id.suaTongNS);
        addNS = findViewById(R.id.addns);
        recyclerView = findViewById(R.id.rview);
        list = db.getAllNS();
        tongNS = db.getTongNganSach();
        luuDong = db.getLuuDong();
        nganSachAdapter = new NganSachAdapter(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        NganSach nganSach = nganSachAdapter.getNganSach(position);
        Intent intent = new Intent(NganSachView.this, SuaNganSach.class);
        intent.putExtra("id", nganSach.getId());
        intent.putExtra("idLoai", nganSach.getLoaiGD().getID());
        intent.putExtra("nganSachHienTai", nganSach.getNganSach());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<NganSach> list = db.getAllNS();
        nganSachAdapter.setList(list);
        recyclerView.setAdapter(nganSachAdapter);
        tongNS = db.getTongNganSach();
        luuDong = db.getLuuDong();
        tongNganSach.setText(String.valueOf(tongNS));
        luuDongText.setText(String.valueOf(luuDong));
    }
}
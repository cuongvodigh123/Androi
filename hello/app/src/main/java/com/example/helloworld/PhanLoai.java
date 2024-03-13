package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import DATABASE.SQLiteHelper;
import adapter.PhanLoaiAdapter;
import adapter.PhanLoaiGDAdapter;
import model.GiaoDich;
import model.LoaiGD;

public class PhanLoai extends AppCompatActivity implements PhanLoaiAdapter.PhanLoaiListen, PhanLoaiGDAdapter.PLGDListen{
    private ImageView back,home;
    private TextView testText;
    private List<LoaiGD> listLoaiGD ;
    private List<GiaoDich> list;
    private RecyclerView recyclerView,recyclerViewGD;
    private PhanLoaiAdapter phanLoaiAdapter ;
    private PhanLoaiGDAdapter phanLoaiGDAdapter;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_loai);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhanLoai.this, MainActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhanLoai.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listLoaiGD = db.getAllLoaiGD();
        phanLoaiAdapter = new PhanLoaiAdapter(this,listLoaiGD);
        phanLoaiAdapter.setPhanLoaiListen(this);
        recyclerView.setAdapter(phanLoaiAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        getDanhsachGD("");
    }
    private void getDanhsachGD(String order){
        list = db.getAllGD(order);
        phanLoaiGDAdapter = new PhanLoaiGDAdapter(list);
        phanLoaiGDAdapter.setPlgdListen((PhanLoaiGDAdapter.PLGDListen) this);
        recyclerViewGD.setAdapter(phanLoaiGDAdapter);
        recyclerViewGD.setLayoutManager(new GridLayoutManager(this,1));
    }
    private void initView(){
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewGD = findViewById(R.id.recyclerViewGD);
        db= new SQLiteHelper(getApplicationContext());

    }

    @Override
    public void onClickLoai(View view, int position) {
        LoaiGD loaiGD = listLoaiGD.get(position);
        Toast.makeText(getApplicationContext(),""+loaiGD.getID(),Toast.LENGTH_SHORT).show();
        getDanhsachGD(String.valueOf(loaiGD.getID()));
    }

    @Override
    public void onClickGD(View view, int positon) {
        GiaoDich giaoDich = list.get(positon);
        System.out.println(giaoDich.getMota()+" Mo ta giao dich");
        Intent intent = new Intent(PhanLoai.this, ChiTiet_GiaoDich.class);
        Bundle args = new Bundle();
        args.putParcelable("keyGD",giaoDich);
        args.putParcelable("keyLoaiGD",giaoDich.getLoaiGD());
        intent.putExtra("giaodich",args);
        intent.putExtra("activity","PhanLoai");
        startActivity(intent);
    }
}
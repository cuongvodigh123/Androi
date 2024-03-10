package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import DATABASE.SQLiteHelper;
import model.GiaoDich;
import model.LoaiGD;

public class ChiTiet_GiaoDich extends AppCompatActivity {
    private TextView idgd,sotien,date,loaigd,mota,kieugd;
    private ImageView back,home;
    private String currentState;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_giao_dich);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<?> previousActivity;
                if (currentState.equals("PhanLoai")) {
                    previousActivity = PhanLoai.class;
                } else {
                    previousActivity = LichSuGiaoDich.class;
                }
                Intent intent = new Intent(ChiTiet_GiaoDich.this, previousActivity);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTiet_GiaoDich.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Bundle args = intent.getBundleExtra("giaodich");
        GiaoDich giaoDich = (GiaoDich) args.getParcelable("keyGD");
        System.out.println(giaoDich.getMota()+" Mo ta giao dich chi tiet");
        LoaiGD loaiGD = (LoaiGD) args.getParcelable("keyLoaiGD");
        idgd.setText("Mã giao dịch: "+ giaoDich.getID());
        sotien.setText("Số tiền: "+giaoDich.getSotien());
        date.setText("Ngày: "+giaoDich.getDate());
        loaigd.setText("Loại giao dịch: "+loaiGD.getNameIcon());
        mota.setText("Mô tả: "+giaoDich.getMota());
        if(giaoDich.getKieugd()==1) kieugd.setText("Tiền chi");
        else kieugd.setText("Tiền thu");
    }
    private void initView(){
        intent = getIntent();
        currentState = intent.getStringExtra("activity");
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);
        idgd = findViewById(R.id.idgd);
        sotien = findViewById(R.id.sotien);
        date = findViewById(R.id.date);
        loaigd = findViewById(R.id.loaigd);
        mota = findViewById(R.id.mota);
        kieugd = findViewById(R.id.kieugd);
    }
}
package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import DATABASE.SQLiteHelper;
import model.LoaiGD;
import model.NganSach;

public class SuaTongNganSach extends AppCompatActivity implements View.OnClickListener {

    private EditText tongNganSach;
    private Button button;
    private ImageView back, home;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_tong_ngan_sach);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaTongNganSach.this, NganSachView.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaTongNganSach.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(this);
    }

    private void initView() {
        tongNganSach = findViewById(R.id.tongNganSach);
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton);
        button = findViewById(R.id.button);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            String nganSachRaw = tongNganSach.getText().toString();
            if (!nganSachRaw.isEmpty()) {
                db = new SQLiteHelper(this);
                float nganSach = Float.parseFloat(nganSachRaw);
                float tongSet = db.getTongNganSach();
                float luuDong = db.getLuuDong();
                if(nganSach < (tongSet - luuDong)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Lỗi ngân sách");
                    builder.setMessage("Tổng ngân sách vừa nhập nhỏ hơn tổng ngân sách thực tế");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    if (db.getNganSachByLoaiGD("1") != null) {
                        NganSach ns = db.getNganSachByLoaiGD("1");
                        LoaiGD loaiGD = db.getLoaiGD("1");
                        NganSach nsNew = new NganSach(ns.getId(), nganSach, loaiGD, "0000");
                        db.updateNS(nsNew);
                        finish();
                    } else {
                        LoaiGD loaiGD = db.getLoaiGD("1");
                        NganSach ns = new NganSach(0, nganSach, loaiGD, "0000");
                        db.addNS(ns);
                        finish();
                    }
                }
            }
        }
    }
}
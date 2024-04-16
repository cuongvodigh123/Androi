package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import DATABASE.SQLiteHelper;
import model.LoaiGD;
import model.NganSach;

public class SuaNganSach extends AppCompatActivity implements View.OnClickListener {

    private ImageView image, back, home;
    private TextView danhmuc, conlai;
    private EditText edit;
    private Button button1, button2;
    private SQLiteHelper db;
    private int id, idLoai;
    private float luudong, nganSachHienTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ngan_sach);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaNganSach.this, NganSachView.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaNganSach.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        idLoai = intent.getIntExtra("idLoai", 0);
        nganSachHienTai = intent.getFloatExtra("nganSachHienTai", 0);
        image.setImageResource(db.getLoaiGD(String.valueOf(idLoai)).getSrcIcon());
        danhmuc.setText(db.getLoaiGD(String.valueOf(idLoai)).getNameIcon());
        edit.setText(String.valueOf(nganSachHienTai));

        luudong = db.getLuuDong() + nganSachHienTai;
        conlai.setText(String.valueOf(luudong));
    }

    private void initView() {
        db = new SQLiteHelper(this);
        image = findViewById(R.id.image1);
        danhmuc = findViewById(R.id.danhmuc);
        conlai = findViewById(R.id.conlai);
        edit = findViewById(R.id.edit);
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
    }

    @Override
    public void onClick(View v) {

        if (v == button1) {
            String nganSachRaw = edit.getText().toString();
            if (!nganSachRaw.isEmpty()) {
                float nganSach = Float.parseFloat(nganSachRaw);
                float luuDong = db.getLuuDong();
                if(nganSach > luuDong){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Lỗi ngân sách");
                    builder.setMessage("Ngân sách vừa nhập lớn hơn số ngân sách còn lại");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    LoaiGD loaiGD = db.getLoaiGD(String.valueOf(idLoai));
                    NganSach nsNew = new NganSach(id, nganSach, loaiGD, "0000");
                    db.updateNS(nsNew);
                    finish();
                }
            }
        }
        if (v == button2){
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc muốn xóa ngân sách này?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.deleteNS(id);
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
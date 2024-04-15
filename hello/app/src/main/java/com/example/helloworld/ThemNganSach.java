package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import DATABASE.SQLiteHelper;
import adapter.SpinerLSAdapter;
import model.LoaiGD;
import model.NganSach;

public class ThemNganSach extends AppCompatActivity implements View.OnClickListener {

    private ImageView back, home;
    private Spinner spinner;
    private Button button;
    private EditText edit;
    private TextView conlai;
    private SQLiteHelper db;
    private List<LoaiGD> listLoaigd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_ngan_sach);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNganSach.this, NganSachView.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNganSach.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(this);
        conlai.setText(String.valueOf(db.getLuuDong()));
    }

    private void initView() {
        db = new SQLiteHelper(this);
        listLoaigd = db.getAllLoaiGDExcept1();
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        edit = findViewById(R.id.edit);
        conlai = findViewById(R.id.tongNganSach);
        SpinerLSAdapter spinerLSAdapter = new SpinerLSAdapter(listLoaigd,this);
        spinner.setAdapter(spinerLSAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            String nganSachRaw = edit.getText().toString();
            if (!nganSachRaw.isEmpty()) {
                String idLoaiRaw = spinner.getSelectedItem().toString();
                int idLoai = Integer.parseInt(idLoaiRaw) + 1;
                if (db.getNganSachByLoaiGD(String.valueOf(idLoai)) != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Lỗi ngân sách");
                    builder.setMessage("Ngân sách thuộc danh mục này đã tồn tại");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
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
                        NganSach ns = new NganSach(0, nganSach, loaiGD, "0000");
                        db.addNS(ns);
                        finish();
                    }
                }
            }
        }
    }
}
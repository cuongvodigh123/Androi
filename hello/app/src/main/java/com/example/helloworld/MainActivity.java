package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button, button1, button2, button3, button4, button5, button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhapThuChi.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhanLoai.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LichSuGiaoDich.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_loai_giaodich.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NganSachView.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TheoDoiNganSach.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaiDat.class);
                startActivity(intent);
            }
        });

        //Thông báo vượt mức
        Intent intent = getIntent();
        if(intent != null){
            if (intent.getIntExtra("thongbaotong", 0) == 1 &&
                    intent.getIntExtra("thongbao", 0) == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Vượt mức ngân sách");
                builder.setMessage("Chi tiêu cho danh mục vừa rồi đã vượt mức " + intent.getStringExtra(
                        "vuotmuc") + " và khiến tổng ngân sách vượt mức " +
                        intent.getStringExtra("vuotmuctong"));
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (intent.getIntExtra("thongbaotong", 0) == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Vượt mức ngân sách");
                builder.setMessage("Chi tiêu cho danh mục vừa rồi đã khiến tổng ngân sách vượt mức " +
                        intent.getStringExtra("vuotmuctong"));
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (intent.getIntExtra("thongbao", 0) == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Vượt mức ngân sách");
                builder.setMessage("Chi tiêu cho danh mục vừa rồi đã vượt mức " + intent.getStringExtra(
                        "vuotmuc"));
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    private void initView(){
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
    }
}
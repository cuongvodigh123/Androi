package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;

import java.sql.Date;

import DATABASE.SQLiteHelper;
import model.GiaoDich;
import model.LoaiGD;
import model.NganSach;

import android.Manifest;

public class Nhap_date_mota extends AppCompatActivity {
    private ImageView back,home;
    private TextView textView,textView11,editTextDate;
    private Button button1,button;
    private Bundle args;
    private Intent intent;
    private LoaiGD loaiGD;
    private float sotien;
    private int kieugd;
    private EditText editTextTextMultiLine;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_date_mota);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("sotien",sotien);
                intent1.putExtra("kieugd",kieugd);
                Bundle args1 = new Bundle();
                args1.putParcelable("loaigd",loaiGD);
                intent1.putExtra("args",args1);
                setResult(NhapThuChi.RESULT_OK, intent1);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nhap_date_mota.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = Date.valueOf(editTextDate.getText().toString());
                Intent intent = new Intent(Nhap_date_mota.this, MainActivity.class);

                //Thông báo khi vượt mức
                final java.util.Calendar c = java.util.Calendar.getInstance();
                int month = c.get(java.util.Calendar.MONTH) + 1;
                int year = c.get(java.util.Calendar.YEAR);
                String strcheck1 = String.format("%d-%02d-%02d", year, month, 1);
                String strcheck2 = String.format("%d-%02d-%02d", year, month, c.get(java.util.Calendar.DAY_OF_MONTH));
                Date check1 = Date.valueOf(strcheck1);
                Date check2 = Date.valueOf(strcheck2);
                if (date.compareTo(check1) >= 0 && date.compareTo(check2) <= 0 && kieugd == 1) {
                    NganSach ns = db.getNganSachByLoaiGD(String.valueOf(loaiGD.getID()));
                    if (ns != null) {
                        float tieudung = db.tongChi1DanhMuc(loaiGD, strcheck1, strcheck2) + sotien;
                        float hanmuc = ns.getNganSach();
                        if (ns.getThongBao().charAt(3) == '1' && tieudung > hanmuc) {
                            intent.putExtra("thongbao", 1);
                            intent.putExtra("vuotmuc", "100%");
                        } else if (ns.getThongBao().charAt(2) == '1' && tieudung > (hanmuc*3/4)){
                            intent.putExtra("thongbao", 1);
                            intent.putExtra("vuotmuc", "75%");
                        } else if (ns.getThongBao().charAt(1) == '1' && tieudung > (hanmuc/2)) {
                            intent.putExtra("thongbao", 1);
                            intent.putExtra("vuotmuc", "50%");
                        } else if (ns.getThongBao().charAt(0) == '1' && tieudung > (hanmuc/4)) {
                            intent.putExtra("thongbao", 1);
                            intent.putExtra("vuotmuc", "25%");
                        }
                    }
                    NganSach tongns = db.getNganSachByLoaiGD("1");
                    if (tongns != null) {
                        float hanmuctong = tongns.getNganSach();
                        float tongchitieu = db.tongChi1DanhMuc(null, strcheck1, strcheck2);
                        if (tongns.getThongBao().charAt(3) == '1' && tongchitieu > hanmuctong) {
                            intent.putExtra("thongbaotong", 1);
                            intent.putExtra("vuotmuctong", "100%");
                        } else if (tongns.getThongBao().charAt(2) == '1' && tongchitieu > (hanmuctong * 3 / 4)) {
                            intent.putExtra("thongbaotong", 1);
                            intent.putExtra("vuotmuctong", "75%");
                        } else if (tongns.getThongBao().charAt(1) == '1' && tongchitieu > (hanmuctong / 2)) {
                            intent.putExtra("thongbaotong", 1);
                            intent.putExtra("vuotmuctong", "50%");
                        } else if (tongns.getThongBao().charAt(0) == '1' && tongchitieu > (hanmuctong / 4)) {
                            intent.putExtra("thongbaotong", 1);
                            intent.putExtra("vuotmuctong", "25%");
                        }
                    }
                }

                String mota = editTextTextMultiLine.getText().toString();
                GiaoDich giaoDich = new GiaoDich(1,date,sotien,loaiGD,mota,kieugd);
                long id = db.insertGiaoDich(giaoDich);
                Toast.makeText(getApplicationContext(),"id: "+id,Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });
        args = intent.getBundleExtra("args");
        loaiGD = args.getParcelable("loaigd");
        sotien = intent.getFloatExtra("sotien",0);
        kieugd = intent.getIntExtra("kieugd",1);
        textView.setText(sotien+" đồng");
        textView11.setText(loaiGD.getNameIcon());
        if(kieugd==1) button1.setText("Chi Phí");
        else button1.setText("Thu nhập");
    }
    public void initView(){
        db= new SQLiteHelper(getApplicationContext());
        intent = getIntent();
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);
        textView = findViewById(R.id.textView);
        textView11 = findViewById(R.id.textView11);
        button1 = findViewById(R.id.button1);
        button = findViewById(R.id.button);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);

    }
    public void showDatePickerDialog(View v) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                editTextDate.setText(selectedDate);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }
}
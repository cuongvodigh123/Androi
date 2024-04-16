package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DATABASE.SQLiteHelper;
import adapter.CaiDatAdapter;
import adapter.NganSachAdapter;
import model.NganSach;

public class CaiDat extends AppCompatActivity implements View.OnClickListener {

    private ImageView back, home;

    private RecyclerView recyclerView;

    private CaiDatAdapter caiDatAdapter;

    private CheckBox checkt1, checkt2, checkt3, checkt4;

    private Button saveSetting;

    private List<NganSach> list;

    private String tongns;

    private HashMap<Integer, String> selectedList = new HashMap<Integer, String>();

    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveSetting.setOnClickListener(this);
        if (tongns.charAt(0) == '1') {
            checkt1.setChecked(true);
        }
        if (tongns.charAt(1) == '1') {
            checkt2.setChecked(true);
        }
        if (tongns.charAt(2) == '1') {
            checkt3.setChecked(true);
        }
        if (tongns.charAt(3) == '1') {
            checkt4.setChecked(true);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(caiDatAdapter);
    }

    private void initView(){
        db = new SQLiteHelper(this);
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton);
        checkt1 = findViewById(R.id.checkt1);
        checkt2 = findViewById(R.id.checkt2);
        checkt3 = findViewById(R.id.checkt3);
        checkt4 = findViewById(R.id.checkt4);
        saveSetting = findViewById(R.id.savesetting);
        recyclerView = findViewById(R.id.rviewCaiDat);
        tongns = db.getNganSachByLoaiGD("1").getThongBao();
        list = db.getAllNS();
        caiDatAdapter = new CaiDatAdapter(list);
    }

    @Override
    public void onClick(View v) {
        if (v == saveSetting) {
            char[] caidattong = "0000".toCharArray();
            if (checkt1.isChecked()){
                caidattong[0] ='1';
            } else {
                caidattong[0] ='0';
            }
            if (checkt2.isChecked()){
                caidattong[1] ='1';
            } else {
                caidattong[1] ='0';
            }
            if (checkt3.isChecked()){
                caidattong[2] ='1';
            } else {
                caidattong[2] ='0';
            }
            if (checkt4.isChecked()){
                caidattong[3] ='1';
            } else {
                caidattong[3] ='0';
            }
            db.updateNSThongBao(db.getNganSachByLoaiGD("1").getId(), String.valueOf(caidattong));
            selectedList = caiDatAdapter.getSelectedValues();
//            Log.d("settingList", selectedList.toString());
            for (Map.Entry<Integer, String> entry : selectedList.entrySet()) {
                db.updateNSThongBao(entry.getKey(), entry.getValue());
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Cài đặt đã được lưu thành công");
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
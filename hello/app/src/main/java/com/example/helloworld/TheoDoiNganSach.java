package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import DATABASE.SQLiteHelper;
import adapter.CustomExpandableListAdapter;
import model.NganSach;

public class TheoDoiNganSach extends AppCompatActivity {

    private ImageView home, back;
    private TextView date, tongReal, tongSet, extext;
    private SQLiteHelper db;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private List<NganSach> list;
    private int month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theo_doi_ngan_sach);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheoDoiNganSach.this, MainActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheoDoiNganSach.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
        String selectedDate = String.format(Locale.getDefault(), "Tháng %02d năm %d", month, year);
        date.setText(selectedDate);

        String dateText1 = String.format("%d-%02d-%02d", year, month, 1);
        String dateText2 = String.format("%d-%02d-%02d", year, month, c.get(Calendar.DAY_OF_MONTH));
        float money1 = db.tongChi1DanhMuc(null, dateText1, dateText2);
        float money2 = db.getTongNganSach();
        String text1 = "Đã chi tiêu: " + String.valueOf(money1);
        String text2 = "Ngân sách: " + String.valueOf(money2);

        String tongtext = "Tổng ngân sách ";
        if (money1 > money2) {
            tongtext += "(vượt mức)";
        }
        extext.setText(tongtext);
        tongReal.setText(text1);
        tongSet.setText(text2);

        HashMap<String, List<String>> expandableListDetail = new HashMap<>();
        list = db.getAllNS();
        for (int i = 0; i<list.size(); i++) {
            List<String> giaTri = new ArrayList<>();
            float tieudung = db.tongChi1DanhMuc(list.get(i).getLoaiGD(), dateText1, dateText2);
            float hanmuc = list.get(i).getNganSach();
            String chiTieu = "Đã chi tiêu: " + String.valueOf(tieudung);
            String nganSach = "Ngân sách: " + String.valueOf(hanmuc);
            giaTri.add(chiTieu);
            giaTri.add(nganSach);
            String title = list.get(i).getLoaiGD().getNameIcon();
            if(tieudung > hanmuc) {
                title += " (vượt mức)";
            }
            expandableListDetail.put(title, giaTri);
        }
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(
                this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void initView() {
        db = new SQLiteHelper(this);
        home = findViewById(R.id.homeButton);
        back = findViewById(R.id.backButton);
        date = findViewById(R.id.date);
        extext = findViewById(R.id.extext);
        tongReal = findViewById(R.id.tongreal);
        tongSet = findViewById(R.id.tongset);
        expandableListView = findViewById(R.id.expandableTheoDoi);
    }
}
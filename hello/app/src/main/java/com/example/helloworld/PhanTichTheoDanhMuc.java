package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import adapter.MyPagerAdapter;

//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

//public class BieuDoThuChi extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_bieu_do_thu_chi);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//    }
//}

public class PhanTichTheoDanhMuc extends AppCompatActivity {

    private ImageView backPTTDM, homePTTDM;
    private ViewPager viewPagerPTTDM;
    private TabLayout tabLayoutPTTDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_tich_theo_danh_muc);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        initView();
        setupViewPager();
        backPTTDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhanTichTheoDanhMuc.this, MainActivity.class);
                startActivity(intent);
            }
        });
        homePTTDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhanTichTheoDanhMuc.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        backPTTDM = findViewById(R.id.backPTTDM);
        homePTTDM = findViewById(R.id.homePTTDM);
        viewPagerPTTDM = findViewById(R.id.viewPagerPTTDM);
        tabLayoutPTTDM = findViewById(R.id.tabLayoutPTTDM);
    }
    private void setupViewPager() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(ThuFragment.newInstance(), "Thu");
        pagerAdapter.addFragment(ChiFragment.newInstance(), "Chi");
        viewPagerPTTDM.setAdapter(pagerAdapter);
        tabLayoutPTTDM.setupWithViewPager(viewPagerPTTDM);
    }


}

package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DATABASE.SQLiteHelper;
import model.GiaoDich;

public class BieuDoThuChi extends AppCompatActivity {
    private ImageView backBDTC, homeBDTC;
    private LineChart lineChart;
    private RadioGroup radioGroup;
    private SQLiteHelper sqliteHelper;
    private int selectedRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieu_do_thu_chi);
        initView();
        initChart();
        backBDTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BieuDoThuChi.this, MainActivity.class);
                startActivity(intent);
            }
        });
        homeBDTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BieuDoThuChi.this, MainActivity.class);
                startActivity(intent);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButtonId = checkedId;
                initChart();
            }
        });
    }

    private void initView() {
        backBDTC = findViewById(R.id.backBDTC);
        homeBDTC = findViewById(R.id.homeBDTC);
        lineChart = findViewById(R.id.lineChart);
        radioGroup = findViewById(R.id.radioGroup);
        selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
    }

    private void initChart() {
        // Khởi tạo các danh sách chứa dữ liệu thu và chi
        List<Entry> thuEntries = new ArrayList<>();
        List<Entry> chiEntries = new ArrayList<>();

        // Lấy ngày hiện tại
        Calendar currentDate = Calendar.getInstance();
        // Lấy ngày đầu tiên của tuần/tháng/năm hiện tại
        Calendar startDate = (Calendar) currentDate.clone();

        if (selectedRadioButtonId == R.id.radioButtonWeek) {
            startDate.set(Calendar.DAY_OF_WEEK, startDate.getFirstDayOfWeek());
        } else if (selectedRadioButtonId == R.id.radioButtonMonth) {
            startDate.set(Calendar.DAY_OF_MONTH, 1);
        } else if (selectedRadioButtonId == R.id.radioButtonYear) {
            startDate.set(Calendar.DAY_OF_YEAR, 1);
        }

        sqliteHelper = new SQLiteHelper(getApplicationContext());

        // Số cột sẽ phụ thuộc vào lựa chọn của người dùng
        int totalColumns = getTotalColumns(selectedRadioButtonId);

        for (int i = 0; i < totalColumns; i++) {
            // Lấy ngày bắt đầu và kết thúc của mỗi khoảng thời gian
            Calendar periodStart = (Calendar) startDate.clone();
            Calendar periodEnd = (Calendar) startDate.clone();
            if (selectedRadioButtonId == R.id.radioButtonWeek) {
                periodEnd.add(Calendar.DAY_OF_WEEK, 6);
            } else if (selectedRadioButtonId == R.id.radioButtonMonth) {
                periodEnd.add(Calendar.DAY_OF_MONTH, periodEnd.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
            } else if (selectedRadioButtonId == R.id.radioButtonYear) {
                periodEnd.add(Calendar.MONTH, i + 1);
                periodEnd.set(Calendar.DAY_OF_MONTH, periodEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
            }

            // Lấy dữ liệu giao dịch từ cơ sở dữ liệu cho mỗi khoảng thời gian
            List<GiaoDich> transactionsThu = sqliteHelper.getTransactionsInRange(convertDateFormat(periodStart), convertDateFormat(periodEnd),2);
            List<GiaoDich> transactionsChi = sqliteHelper.getTransactionsInRange(convertDateFormat(periodStart), convertDateFormat(periodEnd),1);

            float totalThu = 0;
            float totalChi = 0;

            for (GiaoDich transaction : transactionsThu) totalThu += transaction.getSotien();
            for (GiaoDich transaction : transactionsChi) totalChi += transaction.getSotien();


            // Thêm dữ liệu vào danh sách
            thuEntries.add(new Entry(i, totalThu));
            chiEntries.add(new Entry(i, totalChi));

            // Di chuyển ngày bắt đầu sang phía trước cho khoảng thời gian tiếp theo
            if (selectedRadioButtonId == R.id.radioButtonWeek) {
                startDate.add(Calendar.DAY_OF_WEEK, 7);
            } else if (selectedRadioButtonId == R.id.radioButtonMonth) {
                startDate.add(Calendar.MONTH, 1);
            } else if (selectedRadioButtonId == R.id.radioButtonYear) {
                startDate.add(Calendar.MONTH, 1);
            }
        }

        // Tạo DataSet cho biểu đồ thu
        LineDataSet thuDataSet = new LineDataSet(thuEntries, "Thu");
        thuDataSet.setColor(Color.GREEN);

        // Tạo DataSet cho biểu đồ chi
        LineDataSet chiDataSet = new LineDataSet(chiEntries, "Chi");
        chiDataSet.setColor(Color.RED);

        // Tạo dữ liệu cho biểu đồ
        LineData lineData = new LineData();
        lineData.addDataSet(thuDataSet);
        lineData.addDataSet(chiDataSet);

        // Đặt dữ liệu cho biểu đồ
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    // Chuyển đổi ngày tháng từ đối tượng Calendar sang chuỗi định dạng yyyy-MM-dd
    private String convertDateFormat(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Java bắt đầu từ 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String formattedDate = String.format("%04d-%02d-%02d", year, month, day);
        return formattedDate;
    }

    private int getTotalColumns(int selectedRadioButtonId) {
        if (selectedRadioButtonId == R.id.radioButtonWeek ) return 7;
        if (selectedRadioButtonId == R.id.radioButtonMonth ) return 4;
        if (selectedRadioButtonId == R.id.radioButtonYear ) return 12;
        return 0;
    }


}
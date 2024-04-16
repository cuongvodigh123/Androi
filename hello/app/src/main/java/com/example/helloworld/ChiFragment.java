package com.example.helloworld;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DATABASE.SQLiteHelper;
import adapter.DanhMucAdapter;
import model.DanhMucItem;
import model.GiaoDich;
import model.LoaiGD;

public class ChiFragment extends Fragment {

    private PieChart pieChartChi;
    private TextView textViewTotalChi, textViewChi, dStart, dEnd;
    private ListView listViewDanhMuc;
    private List<DanhMucItem> danhMucItemList;
    private DanhMucAdapter danhMucAdapter;
    private int inorout = 1;


    private SQLiteHelper sqliteHelper;

    public ChiFragment() {
        // Required empty public constructor
    }

    public static ChiFragment newInstance() {
        return new ChiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chi, container, false);
        pieChartChi = rootView.findViewById(R.id.pieChartChi);
        textViewTotalChi = rootView.findViewById(R.id.textViewTotalChi);
        textViewChi = rootView.findViewById(R.id.textViewChi);
        dStart = rootView.findViewById(R.id.dStartChi);
        dStart.setOnClickListener(this::onClick);
        dEnd = rootView.findViewById(R.id.dEndChi);
        dEnd.setOnClickListener(this::onClick);
        listViewDanhMuc = rootView.findViewById(R.id.listViewDanhMucChi);


        sqliteHelper = new SQLiteHelper(getContext());

        // Khởi tạo danh sách danh mục và adapter
        danhMucItemList = new ArrayList<>();
        danhMucAdapter = new DanhMucAdapter(getContext(), danhMucItemList);
        listViewDanhMuc.setAdapter(danhMucAdapter);

        textViewChi.setText("Tổng chi");
        textViewChi.setTextSize(30f);
        textViewTotalChi.setText("0 đồng");
        textViewTotalChi.setTextSize(30f);

//        PieChartWithTransactionsInRange(null, null, inorout);
        // Lấy danh sách giao dịch trong khoảng thời gian từ getTransactionsInRange

        Button buttonSearch = rootView.findViewById(R.id.buttonSearch);
        if (buttonSearch != null) {
            buttonSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String startDate = "";
                    String endDate = "";
                    if (view.getId() == R.id.buttonSearch) {
                        String startDateText = dStart.getText().toString();
                        String endDateText = dEnd.getText().toString();

                        startDate = convertDateFormat(startDateText);
                        endDate = convertDateFormat(endDateText);
                    }
                    PieChartWithTransactionsInRange(startDate, endDate, inorout);
                }
            });
        }

        return rootView;
    }
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        if(view == dStart){
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    dStart.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },y,m,d);
            dateDialog.show();
        }
        if(view == dEnd){
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    dEnd.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },y,m,d);
            dateDialog.show();
        }
    }
    String formatNumber(double number) {
        String formattedNumber = String.format("%.0f", number);
        StringBuilder result = new StringBuilder();
        int count = 0;

        // Lặp qua từng ký tự từ phải sang trái
        for (int i = formattedNumber.length() - 1; i >= 0; i--) {
            result.insert(0, formattedNumber.charAt(i)); // Chèn ký tự vào đầu chuỗi kết quả

            // Nếu đã đủ 3 ký tự và không phải ký tự cuối cùng, chèn dấu chấm vào
            if (++count == 3 && i != 0) {
                result.insert(0, ',');
                count = 0; // Reset lại biến đếm
            }
        }

        return result.toString();
    }
    public String convertDateFormat(String dateText) {
        String[] parts = dateText.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format: " + dateText);
        }

        // Kiểm tra xem các phần tử có phải là số không
        for (String part : parts) {
            if (!part.matches("\\d+")) { // Kiểm tra xem phần tử có chứa toàn ký tự số không
                throw new IllegalArgumentException("Invalid date format: " + dateText);
            }
        }

        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        // Chèn '0' vào trước ngày và tháng nếu cần
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        // Ghép chuỗi theo định dạng "yyyy-MM-dd"
        return year + "-" + month + "-" + day;
    }

    double formatDouNum(double number) {
        DecimalFormat mFormat = new DecimalFormat("###,###,###.##");
        try {
            Number parsedNumber = mFormat.parse(mFormat.format(number));
            return parsedNumber.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            // Trả về số 0 hoặc giá trị mặc định khác nếu có lỗi xảy ra
            return 0.0;
        }
    }

    private void PieChartWithTransactionsInRange(String startDate, String endDate, int inorout) {
        List<GiaoDich> transactions = sqliteHelper.getTransactionsInRange(startDate, endDate, inorout);
        // Tạo một danh sách các điểm trên biểu đồ
        ArrayList<PieEntry> entries = new ArrayList<>();
        float totalChi = 0;
        int max = 0;

        for (GiaoDich transaction : transactions) {
            totalChi += transaction.getSotien();
            if (max < sqliteHelper.getLoaiGD(transaction.getLoaiGD().getNameIcon()).getID()) max = sqliteHelper.getLoaiGD(transaction.getLoaiGD().getNameIcon()).getID();
        }

        float amount[] = new float[(max+1) * 2];
        String nameGDInstance[] = new String[(max+1) * 2];

        for (GiaoDich transaction : transactions) {
            int id = sqliteHelper.getLoaiGD(transaction.getLoaiGD().getNameIcon()).getID();
            amount[id] += transaction.getSotien();
            if (nameGDInstance[id] == null) {
                nameGDInstance[id] = sqliteHelper.getLoaiGD(transaction.getLoaiGD().getNameIcon()).getNameIcon();
            }
        }

        for (int i = 1; i <= max; i++) {
            if (nameGDInstance[i] != null) {
                entries.add(new PieEntry(amount[i], nameGDInstance[i]));
                danhMucItemList.add(new DanhMucItem(nameGDInstance[i], formatNumber(amount[i])+" đ", formatDouNum((amount[i] * 100) / totalChi)));
            }
            danhMucAdapter.notifyDataSetChanged();
        }

        // Tạo một dòng dữ liệu cho biểu đồ
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(false);

        // Tạo dữ liệu cho biểu đồ
        PieData pieData = new PieData(dataSet);

        // Thiết lập biểu đồ Pie Chart
        pieChartChi.setData(pieData);
        pieChartChi.setDescription(null);

        pieChartChi.animateXY(1000, 1000);
        pieChartChi.invalidate();

        textViewTotalChi.setText(formatNumber(totalChi) + " đồng");
        textViewTotalChi.setTextSize(30f);
    }
}

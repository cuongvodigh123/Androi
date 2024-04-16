package com.example.helloworld;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class MyValueFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###.##"); // Định dạng số với dấu chấm phân tách mỗi ba chữ số
    }

    @Override
    public String getFormattedValue(float value) {
        // Định dạng giá trị và thêm đơn vị đo lường nếu cần
        return mFormat.format(value) + " đ";
    }
    public float getFloatFormattedValue(float value){
        return Float.parseFloat(mFormat.format(value));

    }
}

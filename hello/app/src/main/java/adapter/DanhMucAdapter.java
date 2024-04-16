package adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.List;

import model.DanhMucItem;

public class DanhMucAdapter extends BaseAdapter {
    private Context context;
    private List<DanhMucItem> danhMucItemList;

    public DanhMucAdapter(Context context, List<DanhMucItem> danhMucItemList) {
        this.context = context;
        this.danhMucItemList = danhMucItemList;
    }

    @Override
    public int getCount() {
        return danhMucItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return danhMucItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_danh_muc, null);
        }

        // Lấy thông tin của mục danh mục tại vị trí position
        DanhMucItem item = danhMucItemList.get(position);

        // Hiển thị thông tin lên các TextView trong item_danh_muc.xml
        TextView tenDanhMucTextView = view.findViewById(R.id.tenDanhMucTextView);
        TextView soTienTextView = view.findViewById(R.id.soTienTextView);
        TextView phanTramTextView = view.findViewById(R.id.phanTramTextView);

        tenDanhMucTextView.setText(item.getTenDanhMuc());
        soTienTextView.setText(String.valueOf(item.getSoTien()));
        phanTramTextView.setText(String.valueOf(item.getPhanTram() + "%"));

        return view;
    }
}

package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.HashMap;
import java.util.List;

import model.NganSach;

public class CaiDatAdapter extends RecyclerView.Adapter<CaiDatAdapter.CaiDatViewHolder> {

    private List<NganSach> list;

    private HashMap<Integer, String> selectedValues = new HashMap<>();

    public CaiDatAdapter(List<NganSach> list) {
        this.list = list;
    }

    public void setList(List<NganSach> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public NganSach getNganSach(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public CaiDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
        return new CaiDatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaiDatViewHolder holder, int position) {
        NganSach ns = list.get(position);
        char[] caidat = "0000".toCharArray();
        holder.listItem.setText(ns.getLoaiGD().getNameIcon());
        if(ns.getThongBao().charAt(0) == '1') {
            holder.check1.setChecked(true);
            caidat[0] = '1';
        }
        if(ns.getThongBao().charAt(1) == '1') {
            holder.check2.setChecked(true);
            caidat[1] = '1';
        }
        if(ns.getThongBao().charAt(2) == '1') {
            holder.check3.setChecked(true);
            caidat[2] = '1';
        }
        if(ns.getThongBao().charAt(3) == '1') {
            holder.check4.setChecked(true);
            caidat[3] = '1';
        }
        selectedValues.put(ns.getId(), String.valueOf(caidat));
        holder.check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check1.isChecked()) {
                    caidat[0] = '1';
                } else {
                    caidat[0] = '0';
                }
                selectedValues.put(ns.getId(), String.valueOf(caidat));
            }
        });
        holder.check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check2.isChecked()) {
                    caidat[1] = '1';
                } else {
                    caidat[1] = '0';
                }
                selectedValues.put(ns.getId(), String.valueOf(caidat));
            }
        });
        holder.check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check3.isChecked()) {
                    caidat[2] = '1';
                } else {
                    caidat[2] = '0';
                }
                selectedValues.put(ns.getId(), String.valueOf(caidat));
            }
        });
        holder.check4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check4.isChecked()) {
                    caidat[3] = '1';
                } else {
                    caidat[3] = '0';
                }
                selectedValues.put(ns.getId(), String.valueOf(caidat));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CaiDatViewHolder extends RecyclerView.ViewHolder {

        private TextView listItem;

        private CheckBox check1, check2, check3, check4;

        public CaiDatViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.listItem);
            check1 = itemView.findViewById(R.id.check1);
            check2 = itemView.findViewById(R.id.check2);
            check3 = itemView.findViewById(R.id.check3);
            check4 = itemView.findViewById(R.id.check4);
        }
    }

    public HashMap<Integer, String> getSelectedValues() {
        return selectedValues;
    }
}

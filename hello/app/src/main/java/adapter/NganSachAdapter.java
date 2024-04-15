package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.NganSachView;
import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import model.NganSach;

public class NganSachAdapter extends RecyclerView.Adapter<NganSachAdapter.NganSachViewHolder> {

    private List<NganSach> list;

    private NganSachListener nganSachListener;

    public void setNganSachListener(NganSachListener nganSachListener) {
        this.nganSachListener = nganSachListener;
    }

    public NganSachAdapter(List<NganSach> list) {
        this.list = list;
    }

    public void setList(List<NganSach> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public NganSach getNganSach(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public NganSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngansach,parent,false);
        return new NganSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NganSachViewHolder holder, int position) {
        NganSach nganSach = list.get(position);
        if (nganSach == null){
            return;
        }
        holder.anh.setImageResource(nganSach.getLoaiGD().getSrcIcon());
        holder.loaigd.setText(nganSach.getLoaiGD().getNameIcon());
        holder.ngansach.setText(String.valueOf(nganSach.getNganSach()));
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class NganSachViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView anh;

        private TextView ngansach, loaigd;

        public NganSachViewHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.anh);
            loaigd = itemView.findViewById(R.id.loaigd);
            ngansach = itemView.findViewById(R.id.ngansach);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (nganSachListener != null){
                nganSachListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface NganSachListener {
        void onItemClick(View view, int position);
    }
}

package com.sajorahasan.demoapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sajorahasan.demoapp.R;
import com.sajorahasan.demoapp.model.DemoItem;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    private List<DemoItem> demoItemList;
    private static OnItemClickListener mListener;


    // Define the mListener interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DemoAdapter(List<DemoItem> demoItemList) {
        this.demoItemList = demoItemList;
    }


    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo, parent, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int pos) {
        DemoItem item = demoItemList.get(pos);

        holder.tvLabel.setText(String.valueOf(item.getId()));
        holder.tvDesc.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return demoItemList == null ? 0 : demoItemList.size();
    }

    public DemoItem getItem(int p){
        return demoItemList.get(p);
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder {
        TextView tvLabel, tvDesc;
        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);

            itemView.setOnClickListener(v -> {
                if (mListener != null) mListener.onItemClick(v, getAdapterPosition());
            });
        }
    }
}

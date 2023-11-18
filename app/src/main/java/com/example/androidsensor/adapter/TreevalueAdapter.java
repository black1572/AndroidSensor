package com.example.androidsensor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.androidsensor.R;
import com.example.androidsensor.db.ThreeValue;

import java.util.List;
public class TreevalueAdapter extends RecyclerView.Adapter<TreevalueAdapter.ViewHolder> {

    private List<String> lists;

    public TreevalueAdapter(List<String> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(lists.get(position));
        holder.x.setText(lists.get(position));
        holder.y.setText(lists.get(position));
        holder.z.setText(lists.get(position));
        holder.fyj.setText(lists.get(position));

    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id,x,y,z,fyj;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id);
            x =itemView.findViewById(R.id.tv_x);
            y = itemView.findViewById(R.id.tv_y);
            z = itemView.findViewById(R.id.tv_z);
            fyj = itemView.findViewById(R.id.tv_fyj);
        }
    }
}

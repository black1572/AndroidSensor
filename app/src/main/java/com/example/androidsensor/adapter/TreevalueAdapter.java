package com.example.androidsensor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsensor.R;
import com.example.androidsensor.constants.Constants;
import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.ThreeValue;


import java.util.List;
public class TreevalueAdapter extends RecyclerView.Adapter<TreevalueAdapter.MyViewHolder> {
   private Context context;
   private List<ThreeValue> mThreeValueList;

   public TreevalueAdapter(Context context){
       this.context = context;
   }

    @NonNull
    @Override
    public TreevalueAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_rc,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreevalueAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mThreeValueList.get(position).getId());
        holder.email.setText((int) mThreeValueList.get(position).getX());
        holder.number.setText((int) mThreeValueList.get(position).getY());
        holder.pincode.setText((int) mThreeValueList.get(position).getZ());
        holder.city.setText((int) mThreeValueList.get(position).getFyj());
    }

    @Override
    public int getItemCount() {
        if (mThreeValueList == null) {
            return 0;
        }
        return mThreeValueList.size();
    }

    public void setTasks(List<ThreeValue> personList) {
        mThreeValueList = personList;
        notifyDataSetChanged();
    }

    public List<ThreeValue> getTasks() {

        return mThreeValueList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, pincode, number, city;
        ImageView editImage;
        AppDatabase mDb;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.person_name);
            email = itemView.findViewById(R.id.person_email);
            pincode = itemView.findViewById(R.id.person_pincode);
            number = itemView.findViewById(R.id.person_number);
            city = itemView.findViewById(R.id.person_city);
            editImage = itemView.findViewById(R.id.image);
            editImage.setOnClickListener(v -> {
                int elementId = mThreeValueList.get(getAdapterPosition()).getId();
                Intent i = new Intent(context, RecyclerView.class);
                i.putExtra(Constants.UPDATE_Person_Id, elementId);
                context.startActivity(i);
            });
        }
    }
}

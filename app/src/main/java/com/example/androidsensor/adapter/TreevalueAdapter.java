package com.example.androidsensor.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.androidsensor.R;
import com.example.androidsensor.db.ThreeValue;

import java.util.List;
public class TreevalueAdapter extends BaseQuickAdapter<ThreeValue, BaseViewHolder>{

    public TreevalueAdapter(int layoutResId, @Nullable List<ThreeValue> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ThreeValue threeValue) {
        baseViewHolder.setText(R.id.tv_id,threeValue.id)
                .setText(R.id.tv_name, (int) threeValue.x)
                .setText(R.id.tv_age, (int) threeValue.y)
                .setText(R.id.tv_nickname, (int) threeValue.z)
                .setText(R.id.tv_address, (int) threeValue.fyj);

    }
}

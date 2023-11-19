package com.example.androidsensor;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsensor.adapter.TreevalueAdapter;
import com.google.android.material.appbar.MaterialToolbar;

public class RecycleView extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
    }
    private void initView() {
        MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        RecyclerView rvText = findViewById(R.id.recycleView);
        back(toolbar);

        //获取适配器实例
       TreevalueAdapter stringAdapter = new TreevalueAdapter(getStrings());
        //配置适配器
        rvText.setAdapter(stringAdapter);
        //配置布局管理器
        rvText.setLayoutManager(new LinearLayoutManager(this));


    }
}
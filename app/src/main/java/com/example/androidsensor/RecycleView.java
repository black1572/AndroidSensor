package com.example.androidsensor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsensor.adapter.TreevalueAdapter;
import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.ThreeValue;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity {
    private TreevalueAdapter mAdapter;
    public static final String TAG = MainActivity.class.getSimpleName();
    private List<ThreeValue> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
    }
    private void initView() {

        //列表
        RecyclerView rv = findViewById(R.id.recycleView);
        mAdapter = new TreevalueAdapter(R.layout.item_rc,mList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

    }
}
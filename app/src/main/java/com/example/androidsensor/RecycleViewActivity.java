package com.example.androidsensor;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsensor.adapter.TreevalueAdapter;
import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.AppExecutors;
import com.example.androidsensor.db.ThreeValue;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TreevalueAdapter mAdapter;
    private AppDatabase db;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        initView();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.recycleView_show){


        }
    }

    //初始化
    private void initView() {
        findViewById(R.id.recycleView_show).setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(mAdapter);

        //获取适配器实例
        mAdapter = new TreevalueAdapter(RecycleViewActivity.this);
        //配置适配器
        mRecyclerView.setAdapter(mAdapter);
        //配置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //连接数据库
        db = AppDatabase.getInstance(getApplicationContext());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<ThreeValue> tasks=mAdapter.getTasks();
                        db.threeValueDAO().deletePerson(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }


        }).attachToRecyclerView(mRecyclerView);

    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            final List<ThreeValue> persons = db.threeValueDAO().loadAllThreeValue();
            runOnUiThread(() -> mAdapter.setTasks(persons));
        });
    }


}
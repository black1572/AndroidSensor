package com.example.androidsensor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.androidsensor.adapter.TreevalueAdapter;
import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.ThreeValue;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity implements View.OnClickListener {
    private TreevalueAdapter mAdapter;
    private AppDatabase db;
    public static final String TAG = RecycleView.class.getSimpleName();
    private List<ThreeValue> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        initView();
        initDB();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.recycleView_show){
            //执行查询操作
            queryAll();
        }
    }

    //初始化
    private void initView() {
        findViewById(R.id.recycleView_show).setOnClickListener(this);

        //列表
        RecyclerView rv = findViewById(R.id.recycleView);
        //获取适配器实例
        mAdapter = new TreevalueAdapter(R.layout.item_rc,mList);
        //配置适配器
        rv.setAdapter(mAdapter);
        //配置布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    //初始化数据库
    private void initDB(){
        //本地持久化数据库
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ThreeValueDB")
                //是否允许在主线程上操作数据库，默认false。
                .allowMainThreadQueries()
                //数据库创建和打开的事件会回调到这里，可以再次操作数据库
                .addCallback(new CallBack())
                .build();

    }
    static class CallBack extends RoomDatabase.Callback {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "db create");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d(TAG, "db open");
        }
    }

    private void queryAll() {
        runOnUiThread(() -> {
            mList.clear();
            mList.addAll(db.threeValueDAO().loadAllThreeValue());
            mAdapter.notifyDataSetChanged();
        });
    }


}
package com.example.androidsensor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.ThreeValue;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class BasicActivity extends AppCompatActivity {
    private AppDatabase db;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDB();
    }
    //初始化数据库
    private void initDB(){
        db = AppDatabase.getInstance(getApplicationContext());
    }
    protected void back(MaterialToolbar toolbar) {
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    protected void jumpActivity(final Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void showMsg(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取字符串列表
     */
    protected List<List<Float>> getStrings() {
     /*   List<String> lists = new ArrayList<>();
        int num = (int) (1 + Math.random() * (50 - 10 + 1));
        for (int i = 0; i < num; i++) {
            lists.add("第 " + i + " 条数据");
        }
        return lists;*/
        List<List<Float>> dataList = new ArrayList<>();
        List<ThreeValue> threeValues = db.threeValueDAO().loadAllThreeValue();
        for (ThreeValue threeValue : threeValues) {
            List<Float> dataRow = new ArrayList<>();
            dataRow.add((float) threeValue.getX());
            dataRow.add((float) threeValue.getY());
            dataRow.add((float) threeValue.getZ());
            dataRow.add((float) threeValue.getFyj());
            dataList.add(dataRow);
        }
        return dataList;
    }

    protected List<BasicBean> getBasicBeans() {
        List<BasicBean> lists = new ArrayList<>();
        int num = (int) (1 + Math.random() * (50 - 10 + 1));
        for (int i = 0; i < num; i++) {
            lists.add(new BasicBean("第 " + i + " 条标题","第 " + i + " 条内容"));
        }
        return lists;
    }
}

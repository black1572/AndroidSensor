package com.example.androidsensor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.androidsensor.adapter.TreevalueAdapter;
import com.example.androidsensor.db.AppDatabase;
import com.example.androidsensor.db.ThreeValue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView x,y,z;
    public static final String TAG = MainActivity.class.getSimpleName();
    private float X1,Y1,Z1,FYJ;
    private Button button,btview,save;
    private AppDatabase db;
    private List<ThreeValue> mList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //初始化数据
        initView();

        //获取传感器管理器 SensorManager
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取加速度传感器 Sensor
        Sensor accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //定义传感器事件监听器
        SensorEventListener accelerometerListener = new SensorEventListener() {
            @Override
            //当Senso上报的数据发生改变时,onSensorChanged被调用
            public void onSensorChanged(SensorEvent sensorEvent) {
               //上报的数据会保存在values属性中
                float X = sensorEvent.values[SensorManager.DATA_X];
                float Y = sensorEvent.values[SensorManager.DATA_Y];
                float Z = sensorEvent.values[SensorManager.DATA_Z];

            }


            //当Sensor精度发生改变时,onaccuracychangeed被调用
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
       /* ,mSensor,SensorManager.SENSOR_DELAY_NORMAL,null);*/
        button.setOnClickListener(view -> {
            sm.registerListener(new MyListener(),accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        });


      /*  btview.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(MainActivity.this, RecycleView.class);
            startActivity(intent);
        });*/


    }
    private void initView(){
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
        button = findViewById(R.id.main_bt);
        findViewById(R.id.main_see).setOnClickListener(this);
        findViewById(R.id.main_save).setOnClickListener(this);

        //初始化数据库
        initDB();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.main_save){
            addvalue();
        }else if (view.getId() == R.id.main_see){
            Intent intent = null;
            intent = new Intent(MainActivity.this, RecycleView.class);
            startActivity(intent);
        }
    }

    class MyListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x1 = sensorEvent.values[0];
            float y1 = sensorEvent.values[1];
            float z1 = sensorEvent.values[2];
            x.setText("当前x为:"+x1);
            y.setText("当前x为:"+y1);
            z.setText("当前x为:"+z1);
            //X,Y,Z变量从加速度传感器获取的数据
            X1=sensorEvent.values[0];
            Y1=sensorEvent.values[1];
            Z1=sensorEvent.values[2];
            FYJ = calculatePitch(X1,Y1,Z1);

        }
        //求俯仰角函数
        public float calculatePitch(double ax, double ay, double az) {
            double roll = Math.atan2(ay, az);
            double pitch = Math.atan2(-ax, Math.sqrt(ay * ay + az * az));
            return (float) Math.toDegrees(pitch);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    //初始化数据库
    private void initDB(){
        //本地持久化数据库
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"ThreeValueDB")
                //是否允许在主线程上操作数据库,默认false.
                .allowMainThreadQueries()
                //数据库创建和打开的事件会回调到这里,可以再次操作数据库
                .addCallback(new CallBack())
                .build();
    }
    static class CallBack extends RoomDatabase.Callback{
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

    private void addvalue(){
        db.threeValueDAO().insertPerson(new ThreeValue(X1,Y1,Z1,FYJ));
    }



}
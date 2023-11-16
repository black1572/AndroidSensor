package com.example.androidsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView x,y,z;
    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
        button = findViewById(R.id.main_bt);

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
                float x = sensorEvent.values[SensorManager.DATA_X];
                float Y = sensorEvent.values[SensorManager.DATA_Y];
                float Z = sensorEvent.values[SensorManager.DATA_Z];
                //X,Y,Z变量从加速度传感器获取的数据
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
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
package com.example.scancontact.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.scancontact.R;

public class ChangePictrueActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView hku,cityu,tsu,pku,google,microsoft,apple,intel,steam,amazon,huawei,tencent,airbnb,baidu,facebook,xiaomi;
    public int click = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycard_change_pic);
        hku = findViewById(R.id.hku);
        cityu = findViewById(R.id.cityu);
        tsu = findViewById(R.id.tsu);
        pku = findViewById(R.id.pku);
        google = findViewById(R.id.google);
        microsoft = findViewById(R.id.microsoft);
        apple = findViewById(R.id.apple);
        intel = findViewById(R.id.intel);
        steam = findViewById(R.id.steam);
        amazon = findViewById(R.id.amazon);
        huawei = findViewById(R.id.huawei);
        tencent = findViewById(R.id.tencent);
        airbnb = findViewById(R.id.airbnb);
        baidu = findViewById(R.id.baidu);
        facebook = findViewById(R.id.facebook);
        xiaomi = findViewById(R.id.xiaomi);
        hku.setOnClickListener(this);
        cityu.setOnClickListener(this);
        tsu.setOnClickListener(this);
        pku.setOnClickListener(this);
        google.setOnClickListener(this);
        microsoft.setOnClickListener(this);
        apple.setOnClickListener(this);
        intel.setOnClickListener(this);
        steam.setOnClickListener(this);
        amazon.setOnClickListener(this);
        huawei.setOnClickListener(this);
        tencent.setOnClickListener(this);
        airbnb.setOnClickListener(this);
        baidu.setOnClickListener(this);
        facebook.setOnClickListener(this);
        xiaomi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent data = new Intent();
        click++;
        switch (view.getId()){
            case R.id.hku:
                data.putExtra("id",R.drawable.hku);
                setResult(1,data);
                finish();
                break;
            case R.id.cityu:
                data.putExtra("id",R.drawable.cityu);
                setResult(1,data);
                finish();
                break;
            case R.id.tsu:
                data.putExtra("id",R.drawable.tsu);
                setResult(1,data);
                finish();
                break;
            case R.id.pku:
                data.putExtra("id",R.drawable.pku);
                setResult(1,data);
                finish();
                break;
            case R.id.google:
                data.putExtra("id",R.drawable.google);
                setResult(1,data);
                finish();
                break;
            case R.id.microsoft:
                data.putExtra("id",R.drawable.microsoft);
                setResult(1,data);
                finish();
                break;
            case R.id.apple:
                data.putExtra("id",R.drawable.apple);
                setResult(1,data);
                finish();
                break;
            case R.id.intel:
                data.putExtra("id",R.drawable.intel);
                setResult(1,data);
                finish();
                break;
            case R.id.steam:
                data.putExtra("id",R.drawable.steam);
                setResult(1,data);
                finish();
                break;
            case R.id.amazon:
                data.putExtra("id",R.drawable.amazon);
                setResult(1,data);
                finish();
                break;
            case R.id.huawei:
                data.putExtra("id",R.drawable.huawei);
                setResult(1,data);
                finish();
                break;
            case R.id.tencent:
                data.putExtra("id",R.drawable.tencent);
                setResult(1,data);
                finish();
                break;
            case R.id.airbnb:
                data.putExtra("id",R.drawable.airbnb);
                setResult(1,data);
                finish();
                break;
            case R.id.baidu:
                data.putExtra("id",R.drawable.baidu);
                setResult(1,data);
                finish();
                break;
            case R.id.facebook:
                data.putExtra("id",R.drawable.facebook);
                setResult(1,data);
                finish();
                break;
            case R.id.xiaomi:
                data.putExtra("id",R.drawable.xiaomi);
                setResult(1,data);
                finish();
                break;
            default:
                break;
        }
    }
}

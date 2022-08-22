package com.example.scancontact.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scancontact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.scancontact.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Locale locale;
    public static String lan="cn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    public void changeLanguageToChinese(View v){
        locale = Locale.CHINESE;
        SPUtils.put(MainActivity.this,"language","cn");
        lan = "cn";
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void changeLanguageToEnglish(View v){
        locale = Locale.ENGLISH;
        SPUtils.put(MainActivity.this,"language","en");
        lan = "en";
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //一个修改语言所需要的方法
    @Override
    protected void attachBaseContext(Context newBase) {
        lan = (String) SPUtils.get(newBase,"language","en");
        if(lan.equalsIgnoreCase("cn")){
            locale = Locale.CHINESE;
        }else{
            locale = Locale.ENGLISH;
        }
        if(locale!=null){
            Context context = MyContextWrapper.wrap(newBase, locale);
            super.attachBaseContext(context);
        }
    }







}
package com.example.scancontact.Mycard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scancontact.Contact.CharPortraitView;
import com.example.scancontact.Main.ChangePictrueActivity;
import com.example.scancontact.R;
import com.example.scancontact.data.ContactMy;
import com.example.scancontact.data.ServiceMy;


public class Mycard_new extends AppCompatActivity {
    EditText name, phone, mobile , email , company , address,birthday ,notes;
    private ServiceMy servicemy = null;
    CharPortraitView portrait;
    ImageView imageView;
    String imageId= String.valueOf(R.drawable.cityu);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard_new);
        name = (EditText) findViewById(R.id.textView9);
        phone = (EditText) findViewById(R.id.textView10);
        mobile = (EditText) findViewById(R.id.textView12);
        email = (EditText) findViewById(R.id.textView11);
        company = (EditText) findViewById(R.id.textView13);
        address = (EditText) findViewById(R.id.textView14);
        birthday = (EditText) findViewById(R.id.textView15);
        notes = (EditText) findViewById(R.id.textView16);
        //portrait = (CharPortraitView) findViewById(R.id.imageview3);
        imageView = findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Mycard_new.this, ChangePictrueActivity.class), 1);
            }
        });

        servicemy = new ServiceMy(this);
    }


    TextView vt1 = null;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save:
                String userName = name.getText().toString();
                String userPhone = phone.getText().toString();
                String userMobile = mobile.getText().toString();
                String userEmail = email.getText().toString();
                String userCompany = company.getText().toString();
                String userAddress = address.getText().toString();
                String userBirthday =  birthday.getText().toString();
                String userNotes =  notes.getText().toString();
                if (name.length() == 0|| phone.length() == 0) {
                    Toast.makeText(this, R.string.check, Toast.LENGTH_SHORT).show();
                } else {
                    ContactMy contactmy = new ContactMy();
                    contactmy.setName(userName);
                    contactmy.setPhone(userPhone);
                    contactmy.setMobile(userMobile);
                    contactmy.setEmail(userEmail);
                    contactmy.setCompany(userCompany);
                    contactmy.setAddress(userAddress);
                    contactmy.setBirthday(userBirthday);
                    contactmy.setNotes(userNotes);
                    contactmy.setIcon(imageId);
                    boolean flag = servicemy.save(contactmy);
                    if (flag) {
                        Toast.makeText(Mycard_new.this, R.string.add_success, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Mycard_new.this,MyCardFragment.class);
                        finish();
                        finish();
                    }
                    else
                        Toast.makeText(this, R.string.fail, Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.delete:

                finish();
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            int changeId = data.getExtras().getInt("id");
            imageId = changeId+"";
            if(changeId!=0){
                imageView.setImageResource( Integer.valueOf(changeId).intValue());
            }
        }
    }
}

package com.example.scancontact.Contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.Service;

public class Contact_scan extends AppCompatActivity {
    private TextView username, userphone, usermobile, useremail, usercompany, useraddress, userbirthday, usernotes;
    //    private DatabaseHelper Contact;
    private Service service = null;
    CharPortraitView portrait;
    private String text_receive = null;
    private String[] text_split = null;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_scan);

        username = (TextView) findViewById(R.id.textView9);
        userphone = (TextView) findViewById(R.id.textView10);
        usermobile = (TextView) findViewById(R.id.textView12);
        useremail = (TextView) findViewById(R.id.textView11);
        usercompany = (TextView) findViewById(R.id.textView13);
        useraddress = (TextView) findViewById(R.id.textView14);
        userbirthday = (TextView) findViewById(R.id.textView15);
        usernotes = (TextView) findViewById(R.id.textView16);
        service = new Service(this);
        portrait = (CharPortraitView) findViewById(R.id.imageview3);


        Bundle bundle = getIntent().getExtras();
        text_receive = bundle.getString("contact_info");
        // 文本格式 :name + phone + mobile + email + company + address + birthday + note
        text_split = text_receive.split("&&");
        // 在开头有app识别码,如果是有效二维码则读取，无效二维码则弹窗警告
        if (text_split[0].equals("scan_contact")) {
            username.setText(text_split[1]);
            userphone.setText(text_split[2]);
            usermobile.setText(text_split[3]);
            useremail.setText(text_split[4]);
            usercompany.setText(text_split[5]);
            useraddress.setText(text_split[6]);
            userbirthday.setText(text_split[7]);
            usernotes.setText(text_split[8]);
        }
        else{
            // 创建消息框来显示二维码
            AlertDialog.Builder alert = new AlertDialog.Builder(Contact_scan.this);
            // 设置消息框标题，以及设置点击消息框以外的地方就会关闭
            alert.setTitle(R.string.fail);
            alert.setMessage(R.string.invalid_qr_code);
            // 设置正面按钮以及出发事件
            alert.setPositiveButton(R.string.BACK, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dlg, int sumthin) {
                    finish();
                }
            });
            // 展示消息框
            alert.show();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }

        userphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:"+ userphone.getText()));

                startActivity(intent);
            }
        });
        usermobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:"+ usermobile.getText()));

                startActivity(intent);
            }
        });
        useremail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:"+useremail.getText());
                Intent data=new Intent(Intent.ACTION_SENDTO);
                data.setData(uri);
                //data.putExtra(Intent.EXTRA_CC);
                //data.putExtra(Intent.EXTRA_SUBJECT);
                //data.putExtra(Intent.EXTRA_TEXT);
                startActivity(Intent.createChooser(data, "choose mail type"));
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.info_save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                String name = username.getText().toString();
                String phone = userphone.getText().toString();
                String mobile = usermobile.getText().toString();
                String email = useremail.getText().toString();
                String company = usercompany.getText().toString();
                String address = useraddress.getText().toString();
                String birthday = userbirthday.getText().toString();
                String note = usernotes.getText().toString();
                if (name.length() == 0|| phone.length() == 0) {
                    Toast.makeText(this, R.string.check, Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setPhone(phone);
                    contact.setMobile(mobile);
                    contact.setEmail(email);
                    contact.setCompany(company);
                    contact.setAddress(address);
                    contact.setBirthday(birthday);
                    contact.setNotes(note);
                    boolean flag = service.save(contact);
                    if (flag) {
                        Toast.makeText(Contact_scan.this, R.string.add_success, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                        Toast.makeText(this, R.string.fail, Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return false;
    }
}
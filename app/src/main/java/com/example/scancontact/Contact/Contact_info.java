package com.example.scancontact.Contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

public class Contact_info extends AppCompatActivity {
    //声明button
    private static final int REQUEST_CODE = 1;
    TextView name, phone, mobile , email , company , address,birthday ,notes;

    TextView button2;
    TextView button3;
    TextView button4;
    Button button_qr;
    CharPortraitView portrait;
    int contactId;
    ImageView image;

    private Contact contact=null;
    private Service service=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int REQUEST_CODE = 1;
        setContentView(R.layout.activity_contact_info);
        button_qr  = findViewById(R.id.button2);// Qrcode
        button2 = findViewById(R.id.textView10);
        button3 = findViewById(R.id.textView12);
        button4= findViewById(R.id.textView11);
        name = (TextView) findViewById(R.id.textView9);
        phone = (TextView) findViewById(R.id.textView10);
        mobile = (TextView) findViewById(R.id.textView12);
        email = (TextView) findViewById(R.id.textView11);
        company = (TextView) findViewById(R.id.textView13);
        address = (TextView) findViewById(R.id.textView14);
        birthday = (TextView) findViewById(R.id.textView15);
        notes = (TextView) findViewById(R.id.textView16);
        String contactName;
        portrait = (CharPortraitView) findViewById(R.id.imageview2);

        Intent intent = getIntent();
        contactId = intent.getIntExtra("id", -1);
        if(contactId == -1){
            finish();
        }else {
            service = new Service(this);
            contact = service.getById(contactId);
            name.setText(contact.getName());
            phone.setText(contact.getPhone());
            mobile.setText(contact.getMobile());
            email.setText(contact.getEmail());
            company.setText(contact.getCompany());
            birthday.setText(contact.getBirthday());
            address.setText(contact.getAddress());
            notes.setText(contact.getNotes());
            contactName = name.getText().toString();
            portrait.setContent(contactName.substring(0, 1));
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }

        button_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 消息格式:name + phone + mobile + email + company + address + birthday + note
                String text = "scan_contact&&" +
                        name.getText().toString()+" &&"+ phone.getText().toString()+" &&"+
                        mobile.getText().toString()+" &&"+ email.getText().toString()+" &&"+
                        company.getText().toString()+" &&"+ address.getText().toString()+" &&" +
                        birthday.getText().toString()+" &&"+ notes.getText().toString() + " &&";
                // 根据text生成二维码，并定义一个imageview储存二维码
                ImageView qrcode = new ImageView(Contact_info.this);
                Bitmap QRCODE = createQRcodeImage(text);
                qrcode.setImageBitmap(QRCODE);
                // 创建消息框来显示二维码
                AlertDialog.Builder alertadd = new AlertDialog.Builder(Contact_info.this);
                // 将二维码提到消息框
                alertadd.setView(qrcode);
                // 设置消息框标题，以及设置点击消息框以外的地方就会关闭
                alertadd.setTitle(R.string.QRCODE);
                alertadd.setCancelable(true);
                // 设置正面按钮以及出发事件
                alertadd.setPositiveButton(R.string.BACK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                    }
                });
                // 展示消息框
                alertadd.show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:"+ button2.getText()));

                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:"+ button3.getText()));

                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:"+button4.getText());
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
        getMenuInflater().inflate(R.menu.info_edit_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_edit:
                Intent intent=new Intent(Contact_info.this, Contact_edit.class);
                intent.putExtra("id",contactId);
                startActivity(intent);
                finish();
                return true;
        }
        return false;
    }

    // 根据文本生成二维码的函数，输入为文本，输出为Bitmap图
    private Bitmap createQRcodeImage(String url) {
        int width = 500;
        int height = 500;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}

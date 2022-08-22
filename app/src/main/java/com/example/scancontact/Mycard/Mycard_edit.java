package com.example.scancontact.Mycard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scancontact.Contact.CharPortraitView;
import com.example.scancontact.Contact.Contact_info;
import com.example.scancontact.Main.ChangePictrueActivity;
import com.example.scancontact.R;
import com.example.scancontact.data.ContactMy;
import com.example.scancontact.data.ServiceMy;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;


public class Mycard_edit extends AppCompatActivity {
    private ContactMy contactmy=null;
    private ServiceMy servicemy=null;
    CharPortraitView portrait;
    EditText name, phone, mobile , email , company , address,birthday ,notes;
    Button button_qr_my;
    int contactId;
    ImageView imageView;

    String imageId=String.valueOf(R.drawable.cityu);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard_edit);
        name = (EditText) findViewById(R.id.textView9);
        phone = (EditText) findViewById(R.id.textView10);
        mobile = (EditText) findViewById(R.id.textView12);
        email = (EditText) findViewById(R.id.textView11);
        company = (EditText) findViewById(R.id.textView13);
        address = (EditText) findViewById(R.id.textView14);
        birthday = (EditText) findViewById(R.id.textView15);
        notes = (EditText) findViewById(R.id.textView16);
        imageView = (ImageView) findViewById(R.id.imageView2);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Mycard_edit.this, ChangePictrueActivity.class), 1);
            }
        });
        String contactName;
        portrait = (CharPortraitView) findViewById(R.id.imageview3);
        Intent intent = getIntent();
        contactId = intent.getIntExtra("id", -1);
        if(contactId == -1){
            finish();
        }else {
            servicemy = new ServiceMy(this);
            contactmy = servicemy.getById(contactId);
            name.setText(contactmy.getName());
            phone.setText(contactmy.getPhone());
            mobile.setText(contactmy.getMobile());
            email.setText(contactmy.getEmail());
            company.setText(contactmy.getCompany());
            birthday.setText(contactmy.getBirthday());
            address.setText(contactmy.getAddress());
            notes.setText(contactmy.getNotes());
            contactName = name.getText().toString();
            portrait.setContent(contactName.substring(0, 1));
            if(!contactmy.getIcon().equalsIgnoreCase("")){
                imageView.setImageResource( Integer.valueOf(contactmy.getIcon()));
                imageId=contactmy.getIcon();
            }
        }

        button_qr_my = (Button) findViewById(R.id.button2);
        button_qr_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0 || phone.length() == 0) {
                    Toast.makeText(Mycard_edit.this, R.string.check, Toast.LENGTH_SHORT).show();
                } else {
                    // 消息格式:name + phone + mobile + email + company + address + birthday + note
                    String text = "scan_contact&&" +
                            name.getText().toString() + " &&" + phone.getText().toString() + " &&" +
                            mobile.getText().toString() + " &&" + email.getText().toString() + " &&" +
                            company.getText().toString() + " &&" + address.getText().toString() + " &&" +
                            birthday.getText().toString() + " &&" + notes.getText().toString() + " &&";
                    // 根据text生成二维码，并定义一个imageview储存二维码
                    ImageView qrcode = new ImageView(Mycard_edit.this);
                    Bitmap QRCODE = createQRcodeImage(text);
                    qrcode.setImageBitmap(QRCODE);
                    // 创建消息框来显示二维码
                    AlertDialog.Builder alertadd = new AlertDialog.Builder(Mycard_edit.this);
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
            }
        });

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
                if (name.length() == 0 || phone.length() == 0) {
                    Toast.makeText(this, R.string.check, Toast.LENGTH_SHORT).show();
                } else {
                    ContactMy contactmy = new ContactMy();
                    contactmy.setId(contactId);
                    contactmy.setName(name.getText().toString());
                    contactmy.setPhone(phone.getText().toString());
                    contactmy.setMobile(mobile.getText().toString());
                    contactmy.setEmail(email.getText().toString());
                    contactmy.setCompany(company.getText().toString());
                    contactmy.setAddress(address.getText().toString());
                    contactmy.setBirthday(birthday.getText().toString());
                    contactmy.setNotes(notes.getText().toString());
                    contactmy.setIcon(imageId);
                    boolean flag = servicemy.update(contactmy);
                    if (flag) {
                        Toast.makeText(Mycard_edit.this, R.string.edit_success, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Mycard_edit.this,MyCardFragment.class);
                        finish();
                    }
                    else
                        Toast.makeText(this, R.string.fail, Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.delete:
                servicemy.delete(contactId);
                finish();
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (data != null) {
                int changeId = data.getExtras().getInt("id");
                imageId = changeId + "";
                if (changeId != 0) {
                    imageView.setImageResource(Integer.valueOf(changeId).intValue());

                }
            }
        }
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

package com.example.scancontact.Contact;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.Service;


public class Contact_new extends AppCompatActivity {
    private EditText username, userphone, usermobile, useremail, usercompany, useraddress, userbirthday, usernotes;
    //    private DatabaseHelper Contact;
    private Service service = null;
    CharPortraitView portrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_new);
        username = (EditText) findViewById(R.id.textView9);
        userphone = (EditText) findViewById(R.id.textView10);
        usermobile = (EditText) findViewById(R.id.textView12);
        useremail = (EditText) findViewById(R.id.textView11);
        usercompany = (EditText) findViewById(R.id.textView13);
        useraddress = (EditText) findViewById(R.id.textView14);
        userbirthday = (EditText) findViewById(R.id.textView15);
        usernotes = (EditText) findViewById(R.id.textView16);
        service = new Service(this);
        portrait = (CharPortraitView) findViewById(R.id.imageview3);
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
                        Toast.makeText(Contact_new.this, R.string.add_success, Toast.LENGTH_LONG).show();
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

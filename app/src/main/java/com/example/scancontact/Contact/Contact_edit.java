package com.example.scancontact.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.Service;


public class Contact_edit extends AppCompatActivity {
    private Contact contact=null;
    private Service service=null;
    CharPortraitView portrait;
    EditText name, phone, mobile , email , company , address,birthday ,notes;
    int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        name = (EditText) findViewById(R.id.textView9);
        phone = (EditText) findViewById(R.id.textView10);
        mobile = (EditText) findViewById(R.id.textView12);
        email = (EditText) findViewById(R.id.textView11);
        company = (EditText) findViewById(R.id.textView13);
        address = (EditText) findViewById(R.id.textView14);
        birthday = (EditText) findViewById(R.id.textView15);
        notes = (EditText) findViewById(R.id.textView16);
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
        String contactName;
        int id = item.getItemId();
        switch (id) {
            case R.id.save:
                if (name.length() == 0 || phone.length() == 0) {
                    Toast.makeText(this, R.string.check, Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact();
                    contact.setId(contactId);
                    contact.setName(name.getText().toString());
                    contact.setPhone(phone.getText().toString());
                    contact.setMobile(mobile.getText().toString());
                    contact.setEmail(email.getText().toString());
                    contact.setCompany(company.getText().toString());
                    contact.setAddress(address.getText().toString());
                    contact.setBirthday(birthday.getText().toString());
                    contact.setNotes(notes.getText().toString());
                    contactName = name.getText().toString();
                    portrait.setContent(contactName.substring(0, 1));
                    boolean flag = service.update(contact);
                    if (flag) {
                        Toast.makeText(Contact_edit.this, R.string.edit_success, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Contact_edit.this, DashboardFragment.class );
                        finish();
                    }
                    else
                        Toast.makeText(this, R.string.fail, Toast.LENGTH_LONG).show();
                }
                 return true;
            case R.id.delete:
                service.delete(contactId);
                finish();
                return true;

        }
        return false;
    }
}

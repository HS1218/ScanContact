package com.example.scancontact.Contact;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.scancontact.Contact.Contact_info;
import com.example.scancontact.Contact.Contact_new;
import com.example.scancontact.ListAdaper.ListAdapterContact;
import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.Service;
import com.example.scancontact.zxing.android.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DashboardFragment extends Fragment {

    ListAdapterContact mListAdapter;
    private ListView mListView;
    private List contactsList = null;
    private Service service = null;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.card_list);
        service = new Service(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactsList = service.getByName(""); // get an contacts array


        final String[] name = new String[contactsList.size()];
        final int[] picture = new int[contactsList.size()];
        for (int i = 0; i < contactsList.size(); i++) {
            Contact contact = (Contact) contactsList.get(i);
            name[i] = contact.getName();


        }

        mListAdapter = new ListAdapterContact(getActivity(), name,
                picture);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int
                    i, long l) {
                Contact contact = (Contact) contactsList.get(i);
                Intent intent = new Intent(getActivity(), Contact_info.class);
                intent.putExtra("id", contact.getId());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present
        inflater.inflate(R.menu.top_nav_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_scan:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                return true;
            case R.id.action_new:
                Intent intent = new Intent(getActivity(), Contact_new.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private void goScan(){
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                Intent intent = new Intent(getActivity(), Contact_scan.class);
                Bundle bundle = new Bundle();
                bundle.putString("contact_info", content);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(getActivity(), R.string.Alert_OpenCamera,
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

}
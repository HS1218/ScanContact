package com.example.scancontact.Mycard;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.example.scancontact.ListAdaper.ListAdapterMyCard;
import com.example.scancontact.R;
import com.example.scancontact.data.Contact;
import com.example.scancontact.data.ContactMy;
import com.example.scancontact.data.Service;
import com.example.scancontact.data.ServiceMy;

import java.util.ArrayList;
import java.util.List;


public class MyCardFragment extends Fragment {
    ListView mListView;
    ListAdapterMyCard mListAdapterMyCard;
    private List contactsList = null;
    private ServiceMy servicemy = null;

    public MyCardFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle saveInstanceState){

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home,container,false);


        servicemy = new ServiceMy(getActivity());


        mListView = (ListView) view.findViewById(R.id.card_list);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactsList = servicemy.getByName(""); // get an contacts array
        final String[] name = new String[contactsList.size()];
        final String[] tel = new String[contactsList.size()];
        final String[] email = new String[contactsList.size()];
        final String[] add = new String[contactsList.size()];
        final String[] head = new String[contactsList.size()];
        final String[] company = new String[contactsList.size()];
        for (int i = 0; i < contactsList.size(); i++) {
            ContactMy contactmy = (ContactMy) contactsList.get(i);
            name[i] = contactmy.getName();
            tel[i] = contactmy.getPhone();
            email[i] = contactmy.getEmail();
            add[i] = contactmy.getAddress();
            head[i] = R.drawable.pictures+"";
            company[i] = contactmy.getIcon();
        }
        mListAdapterMyCard = new
                ListAdapterMyCard(getActivity(),name,tel,email,add,head,company);
        mListView.setAdapter(mListAdapterMyCard);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactMy contact = (ContactMy) contactsList.get(position);
                Intent intent = new Intent(getActivity(), Mycard_edit.class);
                intent.putExtra("id", contact.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.new_mycard_menu, menu) ;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int item_id = item.getItemId();
        switch (item_id)
        {
            case R.id.menu_new_mycard:
                Intent intent = new Intent(getActivity(), Mycard_new.class);
                startActivity(intent);
        }
        return true;
    }

}
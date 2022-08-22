package com.example.scancontact.ListAdaper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.scancontact.Contact.CharPortraitView;
import com.example.scancontact.R;

import java.util.Arrays;


public class ListAdapterMyCard extends BaseAdapter {

    Context context;
    String[]values;
    String[]tel;
    String[]email;
    String[]add;
    String[] head;
    String[] company;
    public ListAdapterMyCard(Context context, String[] name, String[] tel, String[] email, String[] add, String[] head, String[] company) {
        this.context = context;
        this.values=name;
        this.tel=tel;
        this.email=email;
        this.add=add;
        this.head=head;
        this.company=company;
    }


    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull
            ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.card_list_items, parent, false);
            viewHolder.txtName = (TextView)
                    convertView.findViewById(R.id.name);
            viewHolder.txtTel = (TextView)
                    convertView.findViewById(R.id.tel);
            viewHolder.txtEmail = (TextView)
                    convertView.findViewById(R.id.email);
            viewHolder.txtAdd = (TextView)
                    convertView.findViewById(R.id.add);
            viewHolder.portrait = (CharPortraitView) convertView.findViewById(R.id.head2);
            viewHolder.company = (ImageView) convertView.findViewById(R.id.company);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtName.setText(context.getResources().getString(R.string.name)+"  "+values[position]);
        viewHolder.txtTel.setText(context.getResources().getString(R.string.phone)+"  "+tel[position]);
        viewHolder.txtEmail.setText(context.getResources().getString(R.string.email)+"  "+email[position]);
        viewHolder.txtAdd.setText(context.getResources().getString(R.string.address)+"  "+add[position]);
            viewHolder.portrait.setContent(values[position].substring(0, 1));
        viewHolder.company.setImageResource(Integer.valueOf(company[position]).intValue());
        return convertView;
    }
    private static class ViewHolder {
        TextView txtName;
        TextView txtTel;
        TextView txtEmail;
        TextView txtAdd;
        CharPortraitView portrait;
        ImageView company;
    }
}
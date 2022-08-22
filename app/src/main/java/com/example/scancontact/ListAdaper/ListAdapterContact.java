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

public class ListAdapterContact extends BaseAdapter {
    Context context;
    private String [] values;
    private int [] images;
    public ListAdapterContact(Context context, String [] values, int []
            images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.images = images;
    }

    public ListAdapterContact() {
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
            convertView = inflater.inflate(R.layout.fragment_home_item, parent, false);
            viewHolder.txtContact = (TextView)
                    convertView.findViewById(R.id.contactName);
            viewHolder.portrait = (CharPortraitView) convertView.findViewById(R.id.user_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtContact.setText(values[position]);
        viewHolder.portrait.setContent(values[position].substring(0, 1));
        return convertView;
    }
    private static class ViewHolder {
        TextView txtContact;
        CharPortraitView portrait;
    }
}

package com.example.scancontact.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class DBOperationMy{
    private DBContact database = null;

    public DBOperationMy(Context context){
        database = new DBContact(context);
    }

    public boolean save(ContactMy contactmy){
        SQLiteDatabase db = database.getWritableDatabase();
        if(contactmy != null){
            ContentValues value = new ContentValues();
            value.put("name", contactmy.getName());
            value.put("phone", contactmy.getPhone());
            value.put("mobile", contactmy.getMobile());
            value.put("email", contactmy.getEmail());
            value.put("company", contactmy.getCompany());
            value.put("address", contactmy.getAddress());
            value.put("birthday", contactmy.getBirthday());
            value.put("notes", contactmy.getNotes());
            value.put("icon", contactmy.getIcon());
            db.insertOrThrow("contactmy", null, value);
            db.close();
            return true;
        }
        else{
            return false;
        }
    }


    public List getByName(String queryName){
        if(queryName == null || queryName.equals("")){
            return getAll();
        }
        List list = null;
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from contactmy where name like ? or phone like ?";
        String[] params = new String[]{"%"+queryName+"%", "%"+queryName+"%"};
        Cursor cursor = db.rawQuery(sql, params);
        list = new ArrayList();
        while(cursor.moveToNext()){
            ContactMy contactmy = new ContactMy();
            contactmy.setId(cursor.getInt(0));
            contactmy.setName(cursor.getString(1));
            contactmy.setPhone(cursor.getString(2));
            contactmy.setMobile(cursor.getString(3));
            contactmy.setEmail(cursor.getString(4));
            contactmy.setCompany(cursor.getString(5));
            contactmy.setAddress(cursor.getString(6));
            contactmy.setBirthday(cursor.getString(7));
            contactmy.setNotes(cursor.getString(8));
            contactmy.setIcon(cursor.getString(9));
            list.add(contactmy);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List getAll(){
        List list = null;
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from contactmy";
        Cursor cursor = db.rawQuery(sql, null);

        list = new ArrayList();
        while(cursor.moveToNext()){
            ContactMy contactmy = new ContactMy();
            contactmy.setId(cursor.getInt(0));
            contactmy.setName(cursor.getString(1));
            contactmy.setPhone(cursor.getString(2));
            contactmy.setMobile(cursor.getString(3));
            contactmy.setEmail(cursor.getString(4));
            contactmy.setCompany(cursor.getString(5));
            contactmy.setAddress(cursor.getString(6));
            contactmy.setBirthday(cursor.getString(7));
            contactmy.setNotes(cursor.getString(8));
            contactmy.setIcon(cursor.getString(9));
            list.add(contactmy);
        }
        cursor.close();
        db.close();
        return list;
    }

    public ContactMy getById(int id){
        ContactMy contactmy = null;
        if(id > 0){
            SQLiteDatabase db = database.getReadableDatabase();
            String sql = "select * from contactMy where _id=?";
            String[] params = new String[] {String.valueOf(id)};
            Cursor cursor = db.rawQuery(sql, params);
            if(cursor.moveToNext()){
                contactmy = new ContactMy();
                contactmy.setId(cursor.getInt(0));
                contactmy.setName(cursor.getString(1));
                contactmy.setPhone(cursor.getString(2));
                contactmy.setMobile(cursor.getString(3));
                contactmy.setEmail(cursor.getString(4));
                contactmy.setCompany(cursor.getString(5));
                contactmy.setAddress(cursor.getString(6));
                contactmy.setBirthday(cursor.getString(7));
                contactmy.setNotes(cursor.getString(8));
                contactmy.setIcon(cursor.getString(9));
            }
            cursor.close();
            db.close();
        }
        return contactmy;
    }


    public boolean update(ContactMy contactmy){
        if(contactmy != null){
            SQLiteDatabase db = database.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put("name", contactmy.getName());
            value.put("phone", contactmy.getPhone());
            value.put("mobile", contactmy.getMobile());
            value.put("email", contactmy.getEmail());
            value.put("company", contactmy.getCompany());
            value.put("address", contactmy.getAddress());
            value.put("birthday", contactmy.getBirthday());
            value.put("notes", contactmy.getNotes());
            value.put("icon", contactmy.getIcon());
            db.update("contactmy", value, "_id=?", new String[]{String.valueOf(contactmy.getId())});
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public void delete(int id){
        if(id > 0){
            SQLiteDatabase db = database.getWritableDatabase();
            String sql = "delete from contactmy where _id = ?";
            Object[] params = new Object[]{String.valueOf(id)};
            db.execSQL(sql, params);
            db.close();
        }
    }
}



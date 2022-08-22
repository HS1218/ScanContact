package com.example.scancontact.data;

import android.content.Context;

import java.util.List;

public class ServiceMy {

    private DBOperationMy dao=null;

    public ServiceMy(Context context){
        dao = new DBOperationMy(context);
    }

    public boolean save(ContactMy contactmy){
        boolean flag = dao.save(contactmy);
        return flag;
    }

    public List getByName(String queryName){
        List list = dao.getByName(queryName);
        return list;
    }

    public ContactMy getById(int id){
        ContactMy contactmy = dao.getById(id);
        return contactmy;
    }

    public boolean update(ContactMy contactmy){
        boolean flag = dao.update(contactmy);
        return flag;
    }

    public void delete(int id){
        dao.delete(id);
    }
}

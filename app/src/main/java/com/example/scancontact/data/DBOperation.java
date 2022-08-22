package com.example.scancontact.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class DBOperation{
	private DBContact database = null;

	public DBOperation(Context context){
		database = new DBContact(context);
	}

	public boolean save(Contact contact){
		SQLiteDatabase db = database.getWritableDatabase();
		if(contact != null){
			ContentValues value = new ContentValues();
			value.put("name", contact.getName());
			value.put("phone", contact.getPhone());
			value.put("mobile", contact.getMobile());
			value.put("email", contact.getEmail());
			value.put("company", contact.getCompany());
			value.put("address", contact.getAddress());
			value.put("birthday", contact.getBirthday());
			value.put("notes", contact.getNotes());
			db.insertOrThrow("contact", null, value);
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
		String sql = "select * from contact where name like ? or phone like ?";
		String[] params = new String[]{"%"+queryName+"%", "%"+queryName+"%"};
		Cursor cursor = db.rawQuery(sql, params);
		list = new ArrayList();
		while(cursor.moveToNext()){
			Contact contact = new Contact();
			contact.setId(cursor.getInt(0));
			contact.setName(cursor.getString(1));
			contact.setPhone(cursor.getString(2));
			contact.setMobile(cursor.getString(3));
			contact.setEmail(cursor.getString(4));
			contact.setCompany(cursor.getString(5));
			contact.setAddress(cursor.getString(6));
			contact.setBirthday(cursor.getString(7));
			contact.setNotes(cursor.getString(8));
			list.add(contact);
		}
		cursor.close();
		db.close();
		return list;
	}

	public List getAll(){
		List list = null;
		SQLiteDatabase db = database.getReadableDatabase();
		String sql = "select * from contact";
		Cursor cursor = db.rawQuery(sql, null);

		list = new ArrayList();
		while(cursor.moveToNext()){
			Contact contact = new Contact();
			contact.setId(cursor.getInt(0));
			contact.setName(cursor.getString(1));
			contact.setPhone(cursor.getString(2));
			contact.setMobile(cursor.getString(3));
			contact.setEmail(cursor.getString(4));
			contact.setCompany(cursor.getString(5));
			contact.setAddress(cursor.getString(6));
			contact.setBirthday(cursor.getString(7));
			contact.setNotes(cursor.getString(8));
			list.add(contact);
		}
		cursor.close();
		db.close();
		return list;
	}

	public Contact getById(int id){
		Contact contact = null;
		if(id > 0){
			SQLiteDatabase db = database.getReadableDatabase();
			String sql = "select * from contact where _id=?";
			String[] params = new String[] {String.valueOf(id)};
			Cursor cursor = db.rawQuery(sql, params);
			if(cursor.moveToNext()){
				contact = new Contact();
				contact.setId(cursor.getInt(0));
				contact.setName(cursor.getString(1));
				contact.setPhone(cursor.getString(2));
				contact.setMobile(cursor.getString(3));
				contact.setEmail(cursor.getString(4));
				contact.setCompany(cursor.getString(5));
				contact.setAddress(cursor.getString(6));
				contact.setBirthday(cursor.getString(7));
				contact.setNotes(cursor.getString(8));
			}
			cursor.close();
			db.close();
		}
		return contact;
	}


	public boolean update(Contact contact){
		if(contact != null){
			SQLiteDatabase db = database.getWritableDatabase();
//			String sql = "update contact set number = ?, name = ?, " +
//			             "phone = ?, email = ?, address = ?, gender = ?, " +
//					     "relationship = ?, remark = ? where _id = ?";
//			Object[] params = new Object[]{contact.getNumber(),contact.getName(),contact.getPhone(),
//					                       contact.getEmail(),contact.getAddress(),contact.getGender(),
//					                       contact.getRelationship(),contact.getRemark(),contact.getId()};
//			db.execSQL(sql, params);
			ContentValues value = new ContentValues();
			value.put("name", contact.getName());
			value.put("phone", contact.getPhone());
			value.put("mobile", contact.getMobile());
			value.put("email", contact.getEmail());
			value.put("company", contact.getCompany());
			value.put("address", contact.getAddress());
			value.put("birthday", contact.getBirthday());
			value.put("notes", contact.getNotes());
			db.update("contact", value, "_id=?", new String[]{String.valueOf(contact.getId())});
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
			String sql = "delete from contact where _id = ?";
			Object[] params = new Object[]{String.valueOf(id)};
			db.execSQL(sql, params);
			db.close();
		}
	}
}



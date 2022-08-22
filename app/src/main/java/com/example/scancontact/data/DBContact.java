package com.example.scancontact.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBContact extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;
	private static String sql = "create table contact (" + "_id integer primary key autoincrement, "
			+ "name text, " + "phone text, " + "mobile text, " + "email text, "
			+ "company text, " + "address text, " + "birthday text, "
			+ "notes text)";
	private static String sql1 = "create table contactmy (" + "_id integer primary key autoincrement, "
			+ "name text, " + "phone text, " + "mobile text, " + "email text, "
			+ "company text, " + "address text, " + "birthday text, "
			+ "notes text,"+"icon text)";

	public DBContact(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql);
		db.execSQL(sql1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}

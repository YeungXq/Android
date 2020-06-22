package com.yxq.home.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yeung on 2020/4/20.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;

    //构造方法(四个参数)
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //创建表的SQL 命令
    public static final String CREATE_BOOK = "create table user ("
            + "id integer primary key autoincrement, "
            + "userId text,"
            + "password text)";

    public static final String CREATE_MESSAGE = "create table message ("
            + "id integer primary key autoincrement, "
            + "time datetime, "
            + "message text)";
    public static final String CREATE_ARTICLE = "create table article ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "time datetime, "
            + "url text)";

    //创建数据库:当相同版本的数据库BookStore已经存在了，则MyDatabaseHelper中的onCreate()方法就不会执行了。
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行创建表的SQL命名
        db.execSQL(CREATE_BOOK);
        //  Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(CREATE_MESSAGE);
                break;
            case 2:
                db.execSQL(CREATE_ARTICLE);
                break;
            default:
        }
    }
}
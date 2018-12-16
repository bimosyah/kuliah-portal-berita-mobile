package atina.zaima.portalberitanew.Model.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelperLogin extends SQLiteOpenHelper {

    private static final String DB_NAME = "login2.db";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db = this.getWritableDatabase();

    public DataHelperLogin(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long insert = db.insert("user",null,contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public Boolean isUsernameExist(String username){
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{username});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean checkLogin(String username,String password){
        Cursor cursor = db.rawQuery("Select * from user where username=? and password=?",new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}

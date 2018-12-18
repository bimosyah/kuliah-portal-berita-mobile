package atina.zaima.portalberitanew.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperKomentar extends SQLiteOpenHelper {
    private static final String DB_NAME = "komentar.db";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db = this.getWritableDatabase();

    public HelperKomentar(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table komentar(id text primary key, komentar text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists komentar");
    }

    public boolean insert(String id, String komentar){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("komentar",komentar);
        long insert = db.insert("komentar",null,contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }
}

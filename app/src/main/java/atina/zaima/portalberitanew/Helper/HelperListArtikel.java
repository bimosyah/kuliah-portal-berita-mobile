package atina.zaima.portalberitanew.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperListArtikel extends SQLiteOpenHelper {
    private static final String DB_NAME = "artikel.db";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db = this.getWritableDatabase();

    public HelperListArtikel(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table artikel(id text primary key, judul text, berita text, gambar text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists artikel");
    }

    public boolean insert(String id, String judul, String berita, String gambar){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("judul",judul);
        contentValues.put("berita",berita);
        contentValues.put("gambar",gambar);
        long insert = db.insert("artikel",null,contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }
}

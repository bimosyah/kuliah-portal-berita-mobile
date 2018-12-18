package atina.zaima.portalberitanew.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HelperKategori extends SQLiteOpenHelper {
    private static final String DB_NAME = "kategori.db";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db = this.getWritableDatabase();

    public HelperKategori(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table kategori(id text primary key, kategori text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists kategori");
    }

    public boolean insert(String id, String kategori){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("kategori",kategori);
        long insert = db.insert("kategori",null,contentValues);
        if (insert == -1)
            return false;
        else
            return true;
    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM kategori";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // looping through all rows and adding to list

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}

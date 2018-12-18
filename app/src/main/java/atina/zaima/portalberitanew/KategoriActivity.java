package atina.zaima.portalberitanew;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import atina.zaima.portalberitanew.Adapter.Artikel2Adapter;
import atina.zaima.portalberitanew.Adapter.KategoriAdapter;
import atina.zaima.portalberitanew.Dialog.KategoriDialog;
import atina.zaima.portalberitanew.Helper.HelperKategori;
import atina.zaima.portalberitanew.Helper.HelperListArtikel;
import atina.zaima.portalberitanew.Model.Artikel2;
import atina.zaima.portalberitanew.Model.Kategori;

public class KategoriActivity extends AppCompatActivity implements KategoriDialog.DialogListener {

    String[] list;
    HelperKategori helper;
    FloatingActionButton fab;
    Cursor cursor;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Kategori> mKategoris;
    Kategori kategori;
    String _kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        helper = new HelperKategori(this);
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKategori();
            }
        });

        mKategoris = new ArrayList<>();
        mRecyclerView = findViewById(R.id.rv_kategori);
        RefreshList();
    }

    private void RefreshList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kategori",null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String __kategori = cursor.getString(1);

            kategori = new Kategori(id,__kategori);
            mKategoris.add(kategori);
        }

        mAdapter = new KategoriAdapter(mKategoris,getApplicationContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addKategori() {
        KategoriDialog kategoriDialog = new KategoriDialog();
        kategoriDialog.show(getSupportFragmentManager(),"Kategori");
    }

    @Override
    public void applyText(String kategori) {
        _kategori = kategori;
        Random random = new Random();
        int id = random.nextInt(10000);
        Boolean insert = helper.insert(String.valueOf(id),_kategori);
        if (insert == true){
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Insert Success!", Toast.LENGTH_LONG).show();
        }
    }
}

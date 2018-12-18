package atina.zaima.portalberitanew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import atina.zaima.portalberitanew.Adapter.ArtikelAdapter;
import atina.zaima.portalberitanew.Model.Artikel;
import atina.zaima.portalberitanew.Model.GetArtikel;
import atina.zaima.portalberitanew.Rest.ApiClient;
import atina.zaima.portalberitanew.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_artikel);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClint().create(ApiInterface.class);
        ma = this;
        refresh();

    }

    private void refresh() {
        Call<GetArtikel> artikelCall = mApiInterface.getArtikel();
        artikelCall.enqueue(new Callback<GetArtikel>() {
            @Override
            public void onResponse(Call<GetArtikel> call, Response<GetArtikel> response) {
                List<Artikel> artikelList = response.body().getListDataArtikel();
                Log.d("Retrofit get", "Jumlah data artikel: " + String.valueOf(artikelList.size()));

                mAdapter = new ArtikelAdapter(artikelList, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetArtikel> call, Throwable t) {
                Log.d("Retrofit get", t.toString());
                Toast.makeText(getApplicationContext(), "Server tidak konek", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void drawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,1);
                Intent intent = new Intent(getApplicationContext(),ListInputArtikelActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_keluar:
                finishAndRemoveTask();
                break;
        }
        return true;
    }
 
}

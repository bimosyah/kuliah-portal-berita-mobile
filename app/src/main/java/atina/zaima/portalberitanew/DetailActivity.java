package atina.zaima.portalberitanew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import atina.zaima.portalberitanew.Model.Komentar;
import atina.zaima.portalberitanew.Rest.ApiClient;
import atina.zaima.portalberitanew.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView,submit_komentar;
    EditText etKomentar;
    TextView isi,judul,komentar;
    ApiInterface mApiInterface;
    List<Komentar> komentars;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        imageView = (ImageView) findViewById(R.id.gambar_artikel);
        isi = (TextView) findViewById(R.id.tv_isi);
        judul = (TextView) findViewById(R.id.tv_judul);
        mApiInterface = ApiClient.getClint().create(ApiInterface.class);
        submit_komentar = (ImageView) findViewById(R.id.submit_komentar);
        etKomentar = (EditText) findViewById(R.id.editTextKomentar);
        komentar = (TextView) findViewById(R.id.tv_komentar);

        Intent mIntent = getIntent();
        final String id_artikel = mIntent.getStringExtra("id");

        if (mIntent.getStringExtra("asal").equals("artikel2")){
            path = ApiClient.BASE_URL_IMG2;
        }else {
            path = ApiClient.BASE_URL_IMG;
        }

        Picasso.get()
                .load(path + mIntent.getStringExtra("foto"))
                .into(imageView);
        judul.setText(mIntent.getStringExtra("judul"));
        isi.setText(Html.fromHtml(mIntent.getStringExtra("isi")).toString());


        submit_komentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String komentar = etKomentar.getText().toString();

                Call<ResponseBody> call = mApiInterface.insertKomentar(Integer.valueOf(id_artikel),komentar);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s = response.body().string();
                            Toast.makeText(getApplicationContext(),"Komentar telah masuk",Toast.LENGTH_SHORT).show();
                            etKomentar.setText("");
                            finish();
                            startActivity(getIntent());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        final StringBuilder s = new StringBuilder();
        Call<List<Komentar>> call = mApiInterface.getKomentar(id_artikel);
        call.enqueue(new Callback<List<Komentar>>() {
            @Override
            public void onResponse(Call<List<Komentar>> call, Response<List<Komentar>> response) {
                komentars = response.body();
                for (Komentar k : komentars){
                    s.append("Komentar : " + k.getKomentar() + "\n");
                    Log.d("Komentar",k.getKomentar());
                }
                komentar.setText(s);
            }

            @Override
            public void onFailure(Call<List<Komentar>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



}

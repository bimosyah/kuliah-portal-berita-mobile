package atina.zaima.portalberitanew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;

import atina.zaima.portalberitanew.Helper.HelperKategori;
import atina.zaima.portalberitanew.Helper.HelperListArtikel;
import atina.zaima.portalberitanew.Model.Image;
import atina.zaima.portalberitanew.Rest.ApiClient;
import atina.zaima.portalberitanew.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView imageView;
    HelperListArtikel helperListArtikel;
    EditText artikel,judul;
    Button submit;
    Bitmap bitmap;
    Spinner spinner;
    String kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.image_camera);
        helperListArtikel = new HelperListArtikel(this);
        spinner = findViewById(R.id.spinner_kategori);
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        bitmap = intent.getParcelableExtra("BitmapImage");
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);

        judul = findViewById(R.id.judul);
        artikel = findViewById(R.id.artikel);

        loadSpinnterData();
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _judul = judul.getText().toString();
                String _artikel = artikel.getText().toString();
                Random random = new Random();

                int id = random.nextInt(10000);
                String _namaGambar = String.valueOf(id) + ".jpg";
                Boolean insert = helperListArtikel.insert(String.valueOf(id),_judul,_artikel,_namaGambar,kategori);
                if (insert == true ){
                    uploadImage(String.valueOf(id));
                    Toast.makeText(getApplicationContext(), "Insert Success!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void loadSpinnterData() {
        // database handler
        HelperKategori db = new HelperKategori(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void uploadImage(String title)
    {
        String image = imageToString();
        ApiInterface apiInterface = ApiClient.getClint().create(ApiInterface.class);
        Call<Image> call = apiInterface.uploadImage(title,image);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                Image image = response.body();
                Toast.makeText(CameraActivity.this,"Server response = " + image.getResponse(),Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                Log.d("errornya ",t.getMessage());
            }
        });
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        kategori = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

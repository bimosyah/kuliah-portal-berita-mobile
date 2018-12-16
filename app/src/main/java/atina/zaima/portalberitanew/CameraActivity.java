package atina.zaima.portalberitanew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import atina.zaima.portalberitanew.Model.Artikel;
import atina.zaima.portalberitanew.Rest.ApiClient;
import atina.zaima.portalberitanew.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    EditText artikel,judul;
    Button submit;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.image_camera);

        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);

        judul = findViewById(R.id.judul);
        artikel = findViewById(R.id.artikel);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _judul = judul.getText().toString();
                String _artikel = artikel.getText().toString();
                String image = imageToString();
                ApiInterface apiInterface = ApiClient.getClint().create(ApiInterface.class);
                Call<Artikel> call = apiInterface.insertBerita(_judul,_artikel,image);
                call.enqueue(new Callback<Artikel>() {
                    @Override
                    public void onResponse(Call<Artikel> call, Response<Artikel> response) {
                        Log.d("keluar",response.message());
                    }

                    @Override
                    public void onFailure(Call<Artikel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
}

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

import atina.zaima.portalberitanew.Model.Image;
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
        bitmap = intent.getParcelableExtra("BitmapImage");
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);

        judul = findViewById(R.id.judul);
        artikel = findViewById(R.id.artikel);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void uploadImage()
    {
        String image = imageToString();
        String title = judul.getText().toString();
        ApiInterface apiInterface = ApiClient.getClint().create(ApiInterface.class);
        Call<Image> call = apiInterface.uploadImage(title,image);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                Image image = response.body();
                Toast.makeText(CameraActivity.this,"Server response = " + image.getResponse(),Toast.LENGTH_LONG).show();
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
}

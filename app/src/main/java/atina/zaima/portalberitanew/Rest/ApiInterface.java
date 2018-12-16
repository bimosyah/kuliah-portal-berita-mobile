package atina.zaima.portalberitanew.Rest;

import java.util.List;

import atina.zaima.portalberitanew.Model.Artikel;
import atina.zaima.portalberitanew.Model.GetArtikel;
import atina.zaima.portalberitanew.Model.Komentar;
import atina.zaima.portalberitanew.Model.PhotoResult;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("berita")
    Call<GetArtikel> getArtikel();

    @GET("komentar?id_komentar=")
    Call<List<Komentar>> getKomentar(@Query("id_artikel") String id_artikel);

    @FormUrlEncoded
    @POST("komentar")
    Call<ResponseBody> insertKomentar(
            @Field("id_artikel") int id_artikel,
            @Field("komentar") String komentar
    );

    @Multipart
    @POST("upload.php")
    Call<PhotoResult> uploadImage(@Part MultipartBody.Part file);

}

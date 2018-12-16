package atina.zaima.portalberitanew.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.8/berita/web-service/";
    public static final String BASE_URL_IMG = "http://192.168.1.8/berita/apps/assets/uploads/";
    private static Retrofit retrofit = null;

    public static Retrofit getClint(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

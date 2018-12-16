package atina.zaima.portalberitanew.Model;

import com.google.gson.annotations.SerializedName;

public class Komentar {
    @SerializedName("id_artikel")
    private String id_artikel;

    @SerializedName("komentar")
    private String komentar;

    public Komentar(String id_artikel, String komentar) {
        this.id_artikel = id_artikel;
        this.komentar = komentar;
    }

    public String getId_artikel() {
        return id_artikel;
    }

    public String getKomentar() {
        return komentar;
    }
}

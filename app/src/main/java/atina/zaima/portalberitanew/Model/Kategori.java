package atina.zaima.portalberitanew.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    private String id;
    private String kategori;

    public Kategori(String id, String kategori) {
        this.id = id;
        this.kategori = kategori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}

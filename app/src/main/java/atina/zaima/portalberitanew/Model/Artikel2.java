package atina.zaima.portalberitanew.Model;

public class Artikel2 {
    String id, judul, berita;

    public Artikel2(String id, String judul, String berita) {
        this.id = id;
        this.judul = judul;
        this.berita = berita;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getBerita() {
        return berita;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }
}

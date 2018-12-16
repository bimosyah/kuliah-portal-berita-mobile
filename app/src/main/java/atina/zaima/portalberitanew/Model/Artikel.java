package atina.zaima.portalberitanew.Model;

import com.google.gson.annotations.SerializedName;

public class Artikel {
    @SerializedName("id_artikel")
    private String id_artikel;
    @SerializedName("judul")
    private String judul;
    @SerializedName("isi")
    private String isi;
    @SerializedName("tgl_dibuat")
    private String tgl_dibuat;
    @SerializedName("foto")
    private String foto;
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public Artikel(String id_artikel, String judul, String isi, String tgl_dibuat, String foto) {
        this.id_artikel = id_artikel;
        this.judul = judul;
        this.isi = isi;
        this.tgl_dibuat = tgl_dibuat;
        this.foto = foto;
    }

    public Artikel(String id_artikel, String judul, String tgl_dibuat) {
        this.id_artikel = id_artikel;
        this.judul = judul;
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getId_artikel() {
        return id_artikel;
    }

    public void setId_artikel(String id_artikel) {
        this.id_artikel = id_artikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

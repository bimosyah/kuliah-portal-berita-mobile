package atina.zaima.portalberitanew.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetArtikel {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Artikel> listDataArtikel;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Artikel> getListDataArtikel() {
        return listDataArtikel;
    }

    public void setListDataArtikel(List<Artikel> listDataArtikel) {
        this.listDataArtikel = listDataArtikel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

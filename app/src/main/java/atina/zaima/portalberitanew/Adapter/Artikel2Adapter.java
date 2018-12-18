package atina.zaima.portalberitanew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atina.zaima.portalberitanew.Model.Artikel2;
import atina.zaima.portalberitanew.R;
import atina.zaima.portalberitanew.Rest.ApiClient;

public class Artikel2Adapter extends RecyclerView.Adapter<Artikel2Adapter.MyViewHolder> {

    private List<Artikel2> mArtikels;
    private Context mContext;

    public Artikel2Adapter(List<Artikel2> mArtikels, Context mContext) {
        this.mArtikels = mArtikels;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_layout2, viewGroup, false);
        return new MyViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String id = mArtikels.get(position).getId();
        Log.d("idnya",id);
        holder.judul.setText(mArtikels.get(position).getJudul());
        holder.isi.setText(mArtikels.get(position).getBerita());
        Picasso.get().
                load(ApiClient.BASE_URL_IMG2+mArtikels.get(position).getGambar());
    }

    @Override
    public int getItemCount() {
        return mArtikels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul,isi;
        ImageView image;

        public MyViewHolder(View itemView) {
                super(itemView);
                judul = itemView.findViewById(R.id.tv_judul);
                image = itemView.findViewById(R.id.gambar_artikel);
                isi = itemView.findViewById(R.id.tv_isi);

        }
    }
}

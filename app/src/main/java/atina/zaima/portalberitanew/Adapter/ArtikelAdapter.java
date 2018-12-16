package atina.zaima.portalberitanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atina.zaima.portalberitanew.DetailActivity;
import atina.zaima.portalberitanew.Model.Artikel;
import atina.zaima.portalberitanew.R;
import atina.zaima.portalberitanew.Rest.ApiClient;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.MyViewHolder> {

    private List<Artikel> mArtikels;
    private Context mContext;

    public ArtikelAdapter(List<Artikel> mArtikels) {
        this.mArtikels = mArtikels;
    }

    public ArtikelAdapter(List<Artikel> mArtikels, Context mContext) {
        this.mArtikels = mArtikels;
        this.mContext = mContext;
    }

    @Override
    public ArtikelAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Artikel temp = mArtikels.get(position);

        String tanggal = mArtikels.get(position).getTgl_dibuat();
        tanggal = tanggal.replaceAll(" .+$", "");

        String isi = mArtikels.get(position).getIsi();
        isi = isi.substring(0,100) + "...";

        holder.judul.setText(mArtikels.get(position).getJudul());
        holder.tanggal.setText(tanggal);
        holder.isi.setText(Html.fromHtml(isi));
        Picasso.get()
                .load(ApiClient.BASE_URL_IMG + mArtikels.get(position).getFoto())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(),DetailActivity.class);
                mIntent.putExtra("id",mArtikels.get(position).getId_artikel());
                mIntent.putExtra("judul",mArtikels.get(position).getJudul());
                mIntent.putExtra("isi",mArtikels.get(position).getIsi());
                mIntent.putExtra("foto",mArtikels.get(position).getFoto());
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArtikels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul,tanggal,isi;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.tv_judul);
            image = (ImageView) itemView.findViewById(R.id.gambar_artikel);
            tanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
            isi = (TextView) itemView.findViewById(R.id.tv_isi);
        }
    }
}

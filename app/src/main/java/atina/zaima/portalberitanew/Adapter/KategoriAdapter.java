package atina.zaima.portalberitanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import atina.zaima.portalberitanew.DetailActivity;
import atina.zaima.portalberitanew.Model.Artikel2;
import atina.zaima.portalberitanew.Model.Kategori;
import atina.zaima.portalberitanew.R;
import atina.zaima.portalberitanew.Rest.ApiClient;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    private List<Kategori> mKategoris;
    private Context mContext;

    public KategoriAdapter(List<Kategori> mKategoris, Context mContext) {
        this.mKategoris = mKategoris;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_layout_kategori, viewGroup, false);
        return new MyViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String id = mKategoris.get(position).getId();
        Log.d("idnya", id);
        holder.kategori.setText(mKategoris.get(position).getKategori());
    }

    @Override
    public int getItemCount() {
        return mKategoris.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView kategori;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            kategori = itemView.findViewById(R.id.tv_kategori);
        }
    }
}

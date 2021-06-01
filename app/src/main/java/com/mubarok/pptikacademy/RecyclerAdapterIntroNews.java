package com.mubarok.pptikacademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapterIntroNews extends RecyclerView.Adapter<RecyclerAdapterIntroNews.ViewHolder> {

    ArrayList<HashMap<String, String>> listdata;
    private IntroActivity context;

//    private int lastSelectedPosition = -1;

    public RecyclerAdapterIntroNews(IntroActivity context, ArrayList<HashMap<String, String>> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.id.setText(listdata.get(position).get("id_berita"));
        holder.judulBerita.setText(listdata.get(position).get("judul_berita"));
        holder.tanggalBerita.setText(listdata.get(position).get("tanggal_berita"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(v.getContext(), NewsActivity.class);
                intent.putExtra("id_berita", listdata.get(position).get("id_berita"));
                intent.putExtra("judul_berita", listdata.get(position).get("judul_berita"));
                intent.putExtra("keterangan", listdata.get(position).get("keterangan"));
                intent.putExtra("tanggal_berita", listdata.get(position).get("tanggal_berita"));
                intent.putExtra("link_berita", listdata.get(position).get("link_berita"));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        CardView cv;
        TextView id, judulBerita, tanggalBerita;
        private Context context;

        public ViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.beritaId);
            judulBerita = (TextView) v.findViewById(R.id.textJudulBerita);
            relativeLayout = v.findViewById(R.id.relativeNews);
            tanggalBerita = (TextView) v.findViewById(R.id.textTanggalBerita);

        }
    }
}

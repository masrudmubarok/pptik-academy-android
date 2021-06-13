package com.mubarok.pptikacademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerAdapterMyCourses extends RecyclerView.Adapter<RecyclerAdapterMyCourses.ViewHolder> {

    ArrayList<HashMap<String, String>> listdata;
    private MyCoursesFragment context;

//    private int lastSelectedPosition = -1;

    public RecyclerAdapterMyCourses(MyCoursesFragment context, ArrayList<HashMap<String, String>> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_courses, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.id.setText(listdata.get(position).get("id_kursus"));
        holder.namaKursus.setText(listdata.get(position).get("nama_kursus"));
        holder.video.setText(listdata.get(position).get("jumlah_video"));
        holder.modul.setText(listdata.get(position).get("jumlah_modul"));
        Glide.with(context.getActivity())
                .load("https://pptikacademy01.000webhostapp.com/assets/icon/"+listdata.get(position).get("icon"))
                .into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(v.getContext(), LearningActivity.class);
                intent.putExtra("id_kursus", listdata.get(position).get("id_kursus"));
                intent.putExtra("nama_kursus", listdata.get(position).get("nama_kursus"));
                intent.putExtra("deskripsi", listdata.get(position).get("deskripsi"));
                intent.putExtra("nama_tutor", listdata.get(position).get("nama_tutor"));
                intent.putExtra("icon", listdata.get(position).get("icon"));
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
        TextView id, namaKursus, video, modul;
        ImageView icon;
        private Context context;

        public ViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.textViewIdKursusImc);
            namaKursus = (TextView) v.findViewById(R.id.textViewNamaKursusImc);
            relativeLayout = v.findViewById(R.id.relativeImc);
            icon = (ImageView) v.findViewById(R.id.iconImc);
            video = (TextView) v.findViewById(R.id.jumlahVideoTextImc);
            modul = (TextView) v.findViewById(R.id.jumlahModulTextImc);

        }
    }
}

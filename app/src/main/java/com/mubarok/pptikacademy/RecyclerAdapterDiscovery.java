package com.mubarok.pptikacademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;


public class RecyclerAdapterDiscovery extends RecyclerView.Adapter<RecyclerAdapterDiscovery.ViewHolder> {

    ArrayList<HashMap<String ,String >> listdata;
    private DiscoveryFragment context;

//    private int lastSelectedPosition = -1;

    public RecyclerAdapterDiscovery(DiscoveryFragment context, ArrayList<HashMap<String , String >> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discovery, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.id.setText(listdata.get(position).get("id_kursus"));
        holder.namaKursus.setText(listdata.get(position).get("nama_kursus"));
        holder.harga.setText(listdata.get(position).get("harga"));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                final ProgressDialog dialog = new ProgressDialog(v.getContext());
//                dialog.setMessage("Loading delete data");
//                final CharSequence[] dialogitem = {"Edit", "Delete"};
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case 0:
//                                Intent iTransaksiedit = new Intent(v.getContext(), TransaksiEditActivity.class);
//                                iTransaksiedit.putExtra("id_transaksi", listdata.get(position).get("id_transaksi"));
//                                iTransaksiedit.putExtra("tanggal", listdata.get(position).get("tanggal"));
//                                iTransaksiedit.putExtra("keterangan", listdata.get(position).get("keterangan"));
//                                iTransaksiedit.putExtra("jenis", listdata.get(position).get("jenis"));
//                                iTransaksiedit.putExtra("jumlah", listdata.get(position).get("jumlah"));
//                                v.getContext().startActivity(iTransaksiedit);
//                                break;
//                            case 1:
//                                AlertDialog.Builder builderDel = new AlertDialog.Builder(v.getContext());
//                                builderDel.setTitle("Hapus Data");
//                                builderDel.setMessage("Apakah anda yakin ingin menghapus data ini?");
//                                builderDel.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Id = listdata.get(position).get("id_transaksi");
//                                        DeleteData(Id);
//                                        Toast.makeText(v.getContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                                builderDel.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                                builderDel.create().show();
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        CardView cv;
        TextView id, namaKursus, harga;
        ImageView imgView;
        private Context context;

        public ViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.textViewIdKursus);
            namaKursus = (TextView) v.findViewById(R.id.textViewNamaKursus);
            harga = (TextView) v.findViewById(R.id.textViewHarga);
            relativeLayout = v.findViewById(R.id.relative);
        }
    }
}

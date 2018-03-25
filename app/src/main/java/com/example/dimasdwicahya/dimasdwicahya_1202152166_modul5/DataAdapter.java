package com.example.dimasdwicahya.dimasdwicahya_1202152166_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by asus a456 on 25/03/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.holder> {
    //deklarasi variabel yang akan digunakan
    private Context mContext;
    private List<Data> list;
    int color;

    //konstruktor
    public DataAdapter(Context cntx, List<Data> list, int color){
        this.mContext=cntx;
        this.list=list;
        this.color=color;
    }

    //menentukan viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_todo, parent, false);
        holder hldr = new holder(view);
        return hldr;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        Data data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Desc.setText(data.getDeskripsi());
        holder.Priority.setText(data.getPrioritas());
        holder.card_view.setCardBackgroundColor(mContext.getResources().getColor(this.color));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public Data getData(int position){
        return list.get(position);
    }

    //untuk menghapus list pada todolist
    public void deleteData(int i){
        //hapus item yang terpilih
        list.remove(i);
        
        //memberi notifikasi item yang dihapus
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable yang akan digunakan
        
        public TextView ToDo, Desc, Priority;
        public CardView card_view;
        public holder(View itemView){
            super(itemView);

            //panggil si data data untuk ditampilkan ke cardviewnya
            ToDo = itemView.findViewById(R.id.tvTodo);
            Desc = itemView.findViewById(R.id.tvDeskripsi);
            Priority = itemView.findViewById(R.id.tvPrioritas);
            card_view = itemView.findViewById(R.id.cardview);
        }
    }
}

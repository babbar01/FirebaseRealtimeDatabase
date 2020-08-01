package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_class extends RecyclerView.Adapter<adapter_class.Viewholder>
{

    Context context;
    ArrayList<product> list;

    public adapter_class(Context context, ArrayList<product> list) {
        this.context = context;
        this.list = list;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvname,tvprice,tvcategory,tvdescription;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvProdName);
            tvprice=itemView.findViewById(R.id.tvProdPrice);
            tvcategory=itemView.findViewById(R.id.tvProdCategory);
            tvdescription=itemView.findViewById(R.id.tvProdDescription);
        }
    }
    @NonNull
    @Override
    public adapter_class.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_class.Viewholder holder, int position) {
        holder.tvname.setText(list.get(position).prod_name);
        holder.tvprice.setText(list.get(position).prod_price);
        holder.tvcategory.setText(list.get(position).category);
        holder.tvdescription.setText(list.get(position).description);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }



}

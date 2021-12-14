package com.example.tp_dbwithroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ContactAdapterView>{
    private final LayoutInflater mInflater;
    private List<Contact> mylist = new ArrayList<>();
    private static ClickListener clickListener;

    Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.item, parent, false);

        return new ContactAdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(ContactAdapterView holder, int position) {
        if (mylist != null) {
        holder.nom.setText(mylist.get(position).getNom());
        holder.prenom.setText(mylist.get(position).getPrenom());
        holder.num.setText(mylist.get(position).getNumero());
        holder.adresses.setText(mylist.get(position).getAdresses());
      }
    }

    public void setMylist(List<Contact> mylist) {
        this.mylist = mylist;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    public Contact getWordAtPosition(int position) {
        return mylist.get(position);
    }

    public static class ContactAdapterView extends RecyclerView.ViewHolder{
        public TextView nom;
        public TextView prenom;
        public TextView num;
        public TextView adresses;
        public Button delete;
        public ContactAdapterView(View itemView) {
         super(itemView);
         nom = itemView.findViewById(R.id.nom);
         prenom = itemView.findViewById(R.id.prenom);
         num = itemView.findViewById(R.id.num);
            adresses= itemView.findViewById(R.id.adresses);
         delete = itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
     }
 }
    public void setOnItemClickListener(ClickListener clickListener) {
        Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}

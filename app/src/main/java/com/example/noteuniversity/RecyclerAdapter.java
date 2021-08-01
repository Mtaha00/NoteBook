package com.example.noteuniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{
    Context context;
    ArrayList<Note> notes=new ArrayList<>();
    setOnDeleteItemListener setOnDeleteItemListener;
    setOnItemClickListener setOnItemClickListener;
    public RecyclerAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_recycler,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Note note=notes.get(position);
        holder.text.setText(note.getText());
        holder.title.setText(note.getTitle());
        holder.dateText.setText(note.getTextDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView dateText;
        TextView text;
        TextView title;
        ImageView deleteBTN;


        public Holder(@NonNull View itemView) {
            super(itemView);
            dateText=itemView.findViewById(R.id.textDate_recycler);
            text=itemView.findViewById(R.id.text_recycler);
            title=itemView.findViewById(R.id.title_recycler);
            deleteBTN=itemView.findViewById(R.id.delete_button_list);

            deleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(setOnDeleteItemListener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnDeleteItemListener.OnDeleteItemListener(getAdapterPosition());
                        notes.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(setOnItemClickListener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnItemClickListener.OnClickItem(getAdapterPosition(),notes.get(getAdapterPosition()));
                    }
                }
            });

        }
    }
    interface setOnDeleteItemListener{
        void OnDeleteItemListener(int position);
    }
    public void onDeleteItemListener(setOnDeleteItemListener OnDeleteItemListener){
        this.setOnDeleteItemListener=OnDeleteItemListener;
    }
    interface setOnItemClickListener{
        void OnClickItem(int position,Note note);
    }
    public void onItemClickListener(setOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener=setOnItemClickListener;
    }



    public void updateList(ArrayList<Note> notes){
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }
    public Note getNote(int position){
        return notes.get(position);
    }

}

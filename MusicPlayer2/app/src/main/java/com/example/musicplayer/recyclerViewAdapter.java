package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder>{

    private final ArrayList<Song> songsList;
    private static ImageButton play;
    private static Context context;
    private static RecyclerViewClickListener listener;

    public recyclerViewAdapter(ArrayList<Song> songsList, RecyclerViewClickListener listener){
        this.songsList = songsList;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView titleTxt, artistTxt;
        private ImageView imageView;
        private recyclerViewAdapter adapter;

        public MyViewHolder(final View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            titleTxt = view.findViewById(R.id.Title);
            artistTxt = view.findViewById(R.id.Artist);
            play = view.findViewById(R.id.play);
            context = itemView.getContext();
            view.setOnClickListener(this);
        }

        public MyViewHolder linkAdapter(recyclerViewAdapter adapter){
            this.adapter = adapter;
            return this;
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String title = songsList.get(position).getTitle();
        String artist = songsList.get(position).getArtist();
        int image = songsList.get(position).getImage();
        holder.titleTxt.setText(title);
        holder.artistTxt.setText(artist);
        holder.imageView.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}

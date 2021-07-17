package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ItemMovieBinding;
import com.example.myapplication.db.Watched;
import com.example.myapplication.fragments.WatchedFragmentDirections;

import java.util.List;

public class WatchedAdapter extends RecyclerView.Adapter<WatchedAdapter.WatchedViewHolder>{


    private List<Watched> watchedList;
    private Context context;
    private ItemMovieBinding itemMovieBinding;

    public WatchedAdapter(Context context, List<Watched> watchedList) {
        this.watchedList = watchedList;
        this.context = context;
    }

    @NonNull
    @Override
    public WatchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        itemMovieBinding = ItemMovieBinding.inflate(inflater,parent,false);
        return new WatchedViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchedViewHolder holder, int position) {

        holder.itemMovieBinding.movieItemTitle.setText(watchedList.get(position).getTitle());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" +watchedList.get(position).getPoster_path()) //image url
                .into(holder.itemMovieBinding.movieItemPoster);


        //When the movie item is clicked
        holder.itemMovieBinding.movieItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        WatchedFragmentDirections.actionWatchedFragmentToDetailFragment(watchedList.get(position).getId());
                Navigation.findNavController(v).navigate(action);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (watchedList!=null) {
            return watchedList.size();
        }
        return 0;
    }


    public class WatchedViewHolder extends RecyclerView.ViewHolder {

        public ItemMovieBinding itemMovieBinding;

        public WatchedViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }
    }

    public void setList(List<Watched> watchedList){
        this.watchedList = watchedList;
        notifyDataSetChanged();
    }

}

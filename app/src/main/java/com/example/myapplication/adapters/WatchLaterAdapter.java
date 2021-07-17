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
import com.example.myapplication.db.Want;
import com.example.myapplication.fragments.WatchLaterFragmentDirections;

import java.util.List;

public class WatchLaterAdapter extends RecyclerView.Adapter<WatchLaterAdapter.WantViewHolder>{

    private List<Want> wantList;
    private Context context;
    private ItemMovieBinding itemMovieBinding;

    public WatchLaterAdapter(Context context, List<Want> wantList) {
        this.wantList = wantList;
        this.context = context;
    }

    @NonNull
    @Override
    public WantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        itemMovieBinding = ItemMovieBinding.inflate(inflater,parent,false);
        return new WantViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WantViewHolder holder, int position) {

        holder.itemMovieBinding.movieItemTitle.setText(wantList.get(position).getTitle());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" +wantList.get(position).getPoster_path()) //image url
                .into(holder.itemMovieBinding.movieItemPoster);

        //When the movie item is clicked
        holder.itemMovieBinding.movieItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        WatchLaterFragmentDirections.actionWatchLaterFragmentToDetailFragment(wantList.get(position).getId());
                Navigation.findNavController(v).navigate(action);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (wantList!=null) {
            return wantList.size();
        }
        return 0;
    }


    public class WantViewHolder extends RecyclerView.ViewHolder {

        public ItemMovieBinding itemMovieBinding;

        public WantViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }
    }

    public void setList(List<Want> wantList){
        this.wantList = wantList;
        notifyDataSetChanged();
    }

}

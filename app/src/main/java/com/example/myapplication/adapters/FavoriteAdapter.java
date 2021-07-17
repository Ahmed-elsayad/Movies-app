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
import com.example.myapplication.db.Favorite;
import com.example.myapplication.fragments.FavoriteFragmentDirections;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    private List<Favorite> favoriteList;
    private Context context;
    private ItemMovieBinding itemMovieBinding;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        itemMovieBinding = ItemMovieBinding.inflate(inflater,parent,false);
        return new FavoriteViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        holder.itemMovieBinding.movieItemTitle.setText(favoriteList.get(position).getTitle());

        Glide.with(context).
                load("https://image.tmdb.org/t/p/w500/" +favoriteList.get(position).getPoster_path()) //image url
                .into(holder.itemMovieBinding.movieItemPoster);


        //When the movie item is clicked
        holder.itemMovieBinding.movieItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(favoriteList.get(position).getId());
                Navigation.findNavController(v).navigate(action);

            }
        });





    }

    @Override
    public int getItemCount() {
        if (favoriteList!=null) {
            return favoriteList.size();
        }
        return 0;
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        public ItemMovieBinding itemMovieBinding;

        public FavoriteViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }
    }

    public void setList(List<Favorite> favoriteList){
        this.favoriteList = favoriteList;
        notifyDataSetChanged();
    }

}

package com.example.myapplication.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.db.Favorite;
import com.example.myapplication.db.Want;
import com.example.myapplication.db.Watched;
import com.example.myapplication.model.Movies;
import com.example.myapplication.utils.Info;
import com.example.myapplication.viewModels.MovieListViewModel;

import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private Integer MovieId;
    private HashMap<String,String> query;
    private Movies mMovie;
    private String genre;
    private int hour = 0,min = 0;
    private Boolean inFavoriteList = false;
    private Boolean inWatchedList = false;
    private Boolean inWantList = false;

    private MovieListViewModel movieListViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieListViewModel = new ViewModelProvider(DetailFragment.this).get(MovieListViewModel.class);

        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        MovieId = args.getMovieId();
        observeMovie();

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);

        query = new HashMap<>();
        query.put("api_key", Info.API_KEY);
        query.put("page","1");

        movieListViewModel.getMovieDetail(MovieId,query);


        binding.addFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Removing from favorites list
                if(inFavoriteList == false){
                    movieListViewModel.deleteMovie(MovieId);
                    binding.addFavorite.setImageResource(R.drawable.ic_round_notfavorite);
                    Toast.makeText(getContext(),"Removed from your favorites list.",Toast.LENGTH_SHORT).show();

                }
                //Add to your favorites list
                else{
                    Favorite favorite = new Favorite(mMovie.getId(),mMovie.getPoster_path(),mMovie.getOverview(),mMovie.getRelease_date(),
                            mMovie.getTitle(),mMovie.getVote_average(),mMovie.getRuntime());
                    movieListViewModel.insertMovie(favorite);

                    binding.addFavorite.setImageResource(R.drawable.ic_round_favorite);
                    Toast.makeText(getContext(),"Succesfully added to your favorites list.",Toast.LENGTH_LONG).show();
                    inWatchedList = true;


                }
            }
        });

        binding.addWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Removing from favorites list
                if(inWatchedList){
                    movieListViewModel.deleteWatched(MovieId);
                    binding.addWatched.setImageResource(R.drawable.ic_notwatched);
                    Toast.makeText(getContext(),"Removed from your watched list.",Toast.LENGTH_SHORT).show();

                }
                //Add to your favorites list
                else{

                    Watched watched = new Watched(mMovie.getId(),mMovie.getPoster_path(),mMovie.getOverview(),mMovie.getRelease_date(),
                            mMovie.getTitle(),mMovie.getVote_average(),mMovie.getRuntime());
                    movieListViewModel.insertWatched(watched);
                    binding.addWatched.setImageResource(R.drawable.ic_watched);
                    Toast.makeText(getContext(),"Successfully added to your watched list.",Toast.LENGTH_LONG).show();
                    inWatchedList = true;

                    if(inWantList){
                        movieListViewModel.deleteWant(MovieId);
                        binding.addWantToWatch.setImageResource(R.drawable.ic_notwant_to_watch);

                    }


                }
            }
        });


        binding.addWantToWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inWantList){
                    movieListViewModel.deleteWant(MovieId);
                    binding.addWantToWatch.setImageResource(R.drawable.ic_notwant_to_watch);
                    Toast.makeText(getContext(),"Removed from your want to watch list.",Toast.LENGTH_SHORT).show();

                }

                else{
                    Want want = new Want(mMovie.getId(),mMovie.getPoster_path(),mMovie.getOverview(),mMovie.getRelease_date(),
                            mMovie.getTitle(),mMovie.getVote_average(),mMovie.getRuntime());
                    movieListViewModel.insertWant(want);
                    binding.addWantToWatch.setImageResource(R.drawable.ic_want_to_watch);
                    Toast.makeText(getContext(),"Succesfully added to your want to watch list.",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private void isInFavoriteList(int MovieId) {
        if(movieListViewModel.getFavoriteMovie(MovieId) != null) {
            binding.addFavorite.setImageResource(R.drawable.ic_round_favorite);
            inFavoriteList = true;
        }
        else {
            binding.addFavorite.setImageResource(R.drawable.ic_round_notfavorite);
            inFavoriteList = false;
        }
        binding.addFavorite.setVisibility(View.VISIBLE);
    }

    private void isInWatchedList(int MovieId) {
        if(movieListViewModel.getWatchedMovie(MovieId) != null) {
            binding.addWatched.setImageResource(R.drawable.ic_watched);
            inFavoriteList = true;
        }
        else {
            binding.addWatched.setImageResource(R.drawable.ic_notwatched);
            inFavoriteList = false;
        }
        binding.addWatched.setVisibility(View.VISIBLE);
    }

    private void isInWantToWatchList(int MovieId) {
        if(movieListViewModel.getWantMovie(MovieId) != null) {
            binding.addWantToWatch.setImageResource(R.drawable.ic_want_to_watch);
            inWantList = true;
        }
        else {
            binding.addWantToWatch.setImageResource(R.drawable.ic_notwant_to_watch);
            inWantList = false;
        }
        binding.addWantToWatch.setVisibility(View.VISIBLE);
    }


    public void observeMovie(){

        movieListViewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                mMovie = movies;
                Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500/" + movies.getPoster_path()) //image url
                        .centerCrop()
                        .into(binding.posterMovie);

                binding.toolbar.setTitle(movies.getTitle());
                binding.titleMovie.setText(movies.getTitle());

                //dividing vote average by 2 to fit it in the rating bar which have 5 stars
                binding.ratingBar.setRating(movies.getVote_average()/2);

                //turning minutes into hour and minute
                hour = movies.getRuntime()/60;
                min = movies.getRuntime()%60;
                binding.timeMovie.setText(hour+"h "+min+"m");

                binding.overviewMovie.setText(movies.getOverview());


                genre = "";
                for (int i = 0; i < movies.getGenres().size(); i++){
                    //get ArrayList of Genres
                    if(i ==  movies.getGenres().size() -1)
                        //if it is last element
                        genre+= movies.getGenres().get(i).getName();
                    else
                        //put "|" symbol between genres
                        genre+= movies.getGenres().get(i).getName() + " | ";
                }

                binding.genreMovie.setText(genre);
                binding.overviewMovie.setVisibility(View.VISIBLE);
                isInFavoriteList(MovieId);
                isInWantToWatchList(MovieId);
                isInWatchedList(MovieId);



            }
        });


    }


    //onDestroyView allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



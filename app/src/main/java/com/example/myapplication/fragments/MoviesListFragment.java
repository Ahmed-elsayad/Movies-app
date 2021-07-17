package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.MovieListAdapter;
import com.example.myapplication.databinding.FragmentMoviesListBinding;
import com.example.myapplication.model.Movies;
import com.example.myapplication.utils.Info;
import com.example.myapplication.viewModels.MovieListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesListFragment extends Fragment {

    private FragmentMoviesListBinding binding;
    private MovieListAdapter movieListAdapter;
    private HashMap<String, String> queryMap;
    private String moviesCategory="";
    private ArrayList<Movies> moviesList;
    private MovieListViewModel movieListViewModel;


    public MoviesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoviesListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar6);

        queryMap = new HashMap<>();
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        MoviesListFragmentArgs args = MoviesListFragmentArgs.fromBundle(getArguments());
        moviesCategory = args.getMovieCategory();


        queryMap.put("api_key", Info.API_KEY);
        queryMap.put("page","1");


        ConfigureRecyclerView();
        Observe();
        getMoviesList();




    }


    private void ConfigureRecyclerView() {

        binding.MovieListRv.setLayoutManager(new GridLayoutManager(getContext(),1));
        movieListAdapter = new MovieListAdapter(getContext(),moviesList);
        binding.MovieListRv.setAdapter(movieListAdapter);

    }

    private void Observe() {
        switch (moviesCategory){
            case Info.Popular:
                movieListViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Popular Movies");
                    }
                });
                break;
            case Info.TopRated:
                movieListViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Top Rated Movies");
                    }
                });
                break;
            case Info.Latest:
                movieListViewModel.getLatestMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Latest Movies");
                    }
                });
                break;
            case Info.Upcoming:
                movieListViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
                    @Override
                    public void onChanged(ArrayList<Movies> movies) {
                        movieListAdapter.setList(movies);
                        binding.toolbar6.setTitle("Upcoming Movies");
                    }
                });
                break;
        }
    }

    private void getMoviesList() {
        switch (moviesCategory) {
            case Info.Popular:
                movieListViewModel.getPopularMovies(queryMap);
                break;
            case Info.TopRated:
                movieListViewModel.getTopRatedMovies(queryMap);
                break;
            case Info.Latest:
                movieListViewModel.getLatestMovies(queryMap);
                break;
            case Info.Upcoming:
                movieListViewModel.getUpcomingMovies(queryMap);
                break;
        }
    }

}
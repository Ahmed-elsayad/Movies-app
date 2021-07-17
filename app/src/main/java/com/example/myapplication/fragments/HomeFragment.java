package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.MoviesAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.Movies;
import com.example.myapplication.utils.Info;
import com.example.myapplication.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private MoviesAdapter PopularAdapter, TopRatedAdapter, LatestAdapter, UpcomingAdapter;
    private ArrayList<Movies> latestMovies,popularMovies,topRatedMovies,upcomingMovies;

    private FragmentHomeBinding binding;
    private MovieListViewModel movieListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        return rootView;
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Creating ArrayList's for all categories
        popularMovies = new ArrayList<>();
        topRatedMovies = new ArrayList<>();
        latestMovies = new ArrayList<>();
        upcomingMovies = new ArrayList<>();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Info.API_KEY);
        map.put("page", "1");

        observeData();

        movieListViewModel.getPopularMovies(map);
        movieListViewModel.getTopRatedMovies(map);
        movieListViewModel.getLatestMovies(map);
        movieListViewModel.getUpcomingMovies(map);


        setUpRecyclerView();
        setUpOnClick();

    }


    private void setUpOnClick(){

        //navigate to MovieListFragment When  Popular text view clicked


        binding.seeAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HomeFragmentDirections.ActionHomeFragmentToMoviesListFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMoviesListFragment();
                action.setMovieCategory(Info.Popular);
                Navigation.findNavController(v).navigate(action);


            }
        });

        //navigate to MovieListFragment When  Top Rated text view clicked
        binding.seeAllTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToMoviesListFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMoviesListFragment();
                action.setMovieCategory(Info.TopRated);
                Navigation.findNavController(v).navigate(action);


            }
        });

        //navigate to MovieListFragment When  Latest text view clicked
        binding.seeAllLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToMoviesListFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMoviesListFragment();
                action.setMovieCategory(Info.Latest);
                Navigation.findNavController(v).navigate(action);


            }
        });

        //navigate to MovieListFragment When Upcoming text view clicked
        binding.seeAllUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToMoviesListFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMoviesListFragment();

                action.setMovieCategory(Info.Upcoming);
                Navigation.findNavController(v).navigate(action);


            }
        });

    }

    private void setUpRecyclerView() {

        //LinearLayoutManager arranges the individual elements in a one-dimensional list
        binding.PopularRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        PopularAdapter = new MoviesAdapter(getContext(),popularMovies);
        //set the adapter class to RecyclerView.
        binding.PopularRv.setAdapter(PopularAdapter);


        binding.TopRatedRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        TopRatedAdapter = new MoviesAdapter(getContext(),upcomingMovies);
        binding.TopRatedRv.setAdapter(TopRatedAdapter);

        binding.LatestRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        LatestAdapter = new MoviesAdapter(getContext(),topRatedMovies);
        binding.LatestRv.setAdapter(LatestAdapter);

        binding.UpcomingRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        UpcomingAdapter = new MoviesAdapter(getContext(),popularMovies);
        binding.UpcomingRv.setAdapter(UpcomingAdapter);
    }

    private void observeData() {

        movieListViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                PopularAdapter.setMoviesList(movies);
            }
        });
        movieListViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                TopRatedAdapter.setMoviesList(movies);
            }
        });
        movieListViewModel.getLatestMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                LatestAdapter.setMoviesList(movies);

            }
        });
        movieListViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                UpcomingAdapter.setMoviesList(movies);

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
package com.example.myapplication.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SearchListAdapter;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.model.Movies;
import com.example.myapplication.utils.Info;
import com.example.myapplication.viewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SearchFragment extends Fragment {

    private SearchListAdapter searchListAdapter;
    private ArrayList<Movies> movies;
    private FragmentSearchBinding binding;
    private HashMap<String, String> queryMap;
    private String query;


    private MovieListViewModel movieListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movies = new ArrayList<>();


        SearchFragmentArgs args = SearchFragmentArgs.fromBundle(getArguments());
        query = args.getQuery();

        binding.queryText.setText(query +"");

        queryMap = new HashMap<>();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        queryMap.put("api_key", Info.API_KEY);
        queryMap.put("query",query);

        observeData();


        movieListViewModel.getSearchedMovies(queryMap);

        ConfigureRecyclerView();


        binding.queryText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //queryText has imeOptions="actionSearch"
                    query = binding.queryText.getText().toString().trim().toLowerCase();
                    queryMap.clear();
                    queryMap.put("api_key", Info.API_KEY);
                    queryMap.put("query",query);

                    movieListViewModel.getSearchedMovies(queryMap);
                }
                //return false will  defines if user consume the click search or not
                //while returning false it executes this action
                return false;
            }
        });


        binding.querySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                query = binding.queryText.getText().toString().trim().toLowerCase();
                queryMap.clear();
                queryMap.put("api_key", Info.API_KEY);
                queryMap.put("query",query);

                movieListViewModel.getSearchedMovies(queryMap);
            }
        });


    }

    //Put data into RecyclerView
    private void ConfigureRecyclerView() {
        binding.MoviesearchRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        searchListAdapter = new SearchListAdapter(getContext(), movies);
        binding.MoviesearchRv.setAdapter(searchListAdapter);
    }


    private void observeData() {
        //Observing MutableLiveData<ArrayList<Movies>>
        movieListViewModel.getQueriesMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {

                searchListAdapter.setList(movies);
                //set a list with movies

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
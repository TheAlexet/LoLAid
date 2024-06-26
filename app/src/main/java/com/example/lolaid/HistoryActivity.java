package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import adapters.MatchHistoryAdapter;
import business_logic.data_models.custom_pojo.MatchInfo;
import business_logic.services.RiotApiService;

public class HistoryActivity extends Fragment {

    public RiotApiService riotApiService;
    public MatchHistoryAdapter matchHistoryAdapter;
    public List<MatchInfo> matchesList;
    private SharedPreferences sharedPrefs;
    private String summName;
    private View fragmentView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public HistoryActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_activity, container, false);
        recyclerView = view.findViewById(R.id.rvMatchHistory);
        progressBar = view.findViewById(R.id.progressBarHistory);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int regionSelected = sharedPrefs.getInt("region", 0);
        riotApiService = new RiotApiService(regionSelected);

        summName = sharedPrefs.getString("summonerName","");

        List<MatchInfo> provisionalList = new ArrayList<>();

        matchHistoryAdapter = new MatchHistoryAdapter(provisionalList, this.getActivity(),this);


        RecyclerView recycler = view.findViewById(R.id.rvMatchHistory);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        recycler.setAdapter(matchHistoryAdapter);

        //riotApiService.getMatchesHistoryInfo(summName, this);
        hideAll();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fragmentView = view;
        riotApiService.getMatchesHistoryInfo(summName, this);
    }

    public void getMatchHistoryList(List<MatchInfo> matchHistory)
    {
        matchesList = new ArrayList<>(matchHistory);

        matchHistoryAdapter.setMatches(matchHistory);
    }

    private void hideAll(){
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void showAll(){
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

}

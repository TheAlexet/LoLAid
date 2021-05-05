package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.MatchHistoryAdapter;
import business_logic.data_models.custom_pojo.MatchInfo;
import business_logic.services.RiotApiService;

public class HistoryActivity extends Fragment {

    public RiotApiService riotApiService;
    public MatchHistoryAdapter matchHistoryAdapter;
    public List<MatchInfo> matchesList = new ArrayList<MatchInfo>();

    public HistoryActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_activity, container, false);
        riotApiService = new RiotApiService();
        riotApiService.getMatchInfo("pabletefest", this);
        matchHistoryAdapter = new MatchHistoryAdapter(matchesList);

        RecyclerView recycler = view.findViewById(R.id.rvMatchHistory);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        recycler.setAdapter(matchHistoryAdapter);
        return view;
    }

    public void getMatchHistoryInfo(MatchInfo matchInfo){

    }

}

package com.example.lolaid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import adapters.LiveGameAdapter;
import business_logic.data_models.custom_pojo.LiveMatchInfo;
import business_logic.services.RiotApiService;

public class LiveGameActivity extends Fragment {

    private RiotApiService apiService;
    LiveGameAdapter adapter;

    public LiveGameActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_game_activity, container, false);
        apiService = new RiotApiService(getActivity());
        adapter = new LiveGameAdapter();
        return view;
    }

    public void getLiveMatchInfo(LiveMatchInfo matchInfo){

    }

}

package com.example.lolaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeFragment(View view) {
        Class<? extends Fragment> fragmentClass = null;
        if (view.getId() == R.id.bStats) {
            fragmentClass = StatsActivity.class;
        } else if(view.getId() == R.id.bHistory) {
            fragmentClass = HistoryActivity.class;
        } else if(view.getId() == R.id.bRecommendations) {
            fragmentClass = RecommendationsActivity.class;
        } else if(view.getId() == R.id.bLiveGame) {
            fragmentClass = LiveGameActivity.class;
        }
        if(fragmentClass != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcvView, fragmentClass, null)
                    .commit();
        }
    }

}
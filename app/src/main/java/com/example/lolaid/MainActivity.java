package com.example.lolaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public boolean addToBackStack = false;
    Class<? extends Fragment> previousClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Class<? extends Fragment> firstFragmentClass = DefaultActivity.class;
        previousClass = DefaultActivity.class;
        if(firstFragmentClass != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcvView, firstFragmentClass, null)
                    .commit();
        }
    }

    public void changeFragment(View view) {
        Class<? extends Fragment> fragmentClass = null;
        if (view.getId() == R.id.bStats) {
            fragmentClass = StatsActivity.class;
            previousClass = StatsActivity.class;
            addToBackStack = false;
        } else if(view.getId() == R.id.bHistory) {
            fragmentClass = HistoryActivity.class;
            previousClass = HistoryActivity.class;
            addToBackStack = false;
        } else if(view.getId() == R.id.bRecommendations) {
            fragmentClass = RecommendationsActivity.class;
            previousClass = RecommendationsActivity.class;
            addToBackStack = false;
        } else if(view.getId() == R.id.bLiveGame) {
            fragmentClass = LiveGameActivity.class;
            previousClass = LiveGameActivity.class;
            addToBackStack = false;
        } else if(view.getId() == R.id.bSettings) {
            fragmentClass = SettingsActivity.class;
            addToBackStack = false;
        } else if(view.getId() == R.id.bClose) {
            fragmentClass = previousClass;
            addToBackStack = false;
        }
        if(fragmentClass != null) {
            if(addToBackStack) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvView, fragmentClass, null)
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvView, fragmentClass, null)
                        .commit();
            }

        }
    }

}
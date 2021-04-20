package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class SettingsActivity extends Fragment {

    private EditText summonerName;
    private SharedPreferences sharedPrefs;
    private Spinner language;

    public SettingsActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        summonerName =  getView().findViewById(R.id.summonerNameBox);
        language = getView().findViewById(R.id.settingsLanguageSpinner);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        summonerName.setText(sharedPrefs.getString("summonerName", ""));
        language.setSelection(sharedPrefs.getInt("language", 0));

        summonerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.putString("summonerName", summonerName.getText().toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("language", position);
                editor.apply();
                switch(position) {
                    case 0:
                        setLocale(Locale.getDefault().toLanguageTag());
                        break;
                    case 1:
                        setLocale("es");
                        break;
                    case 2:
                        setLocale("ca");
                        break;
                }
                //refreshView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    private void refreshView() {
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.settingsActivityId, this);
        fragTransaction.commit();
    }

}

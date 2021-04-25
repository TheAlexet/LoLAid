package business_logic.services;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

import business_logic.data_models.SummonerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RiotApiService
{
    private final String RIOT_API_KEY = "RGAPI-68265d1b-ee18-4ec3-8e72-dd0703725ba0";
    private final String RANKED_SOLO = "RANKED_SOLO_5x5";
    private final String RANKED_FLEX = "RANKED_FLEX_SR";

    private IRiotApiServiceREST service;

    public RiotApiService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://euw1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);
    }

    public void printSummonerByNameTest()
    {
        Call<SummonerDTO> call = service.getSummonerByName("pabletefest", RIOT_API_KEY);

        try
        {
            SummonerDTO summoner = call.execute().body();
            Log.d("ID", summoner.getId());
            Log.d("ACCOUNT_ID", summoner.getAccountId());
            Log.d("LEVEL", summoner.getSummonerLevel() + "");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSummonerByNameTestAsync()
    {

        Call<SummonerDTO> call = service.getSummonerByName("pabletefest", RIOT_API_KEY);

        call.enqueue(new Callback<SummonerDTO>()
        {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response)
            {
                SummonerDTO summoner = response.body();
                Log.d("ID", summoner.getId());
                Log.d("ACCOUNT_ID", summoner.getAccountId());
                Log.d("LEVEL", summoner.getSummonerLevel() + "");
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t)
            {

            }
        });
    }

    public void getSummonerByName(String summonerName, Activity activity)
    {
        Call<SummonerDTO> call = service.getSummonerByName(summonerName, RIOT_API_KEY);

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response) {
                if (response.isSuccessful())
                {
                    SummonerDTO summoner = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t) {

            }
        });
    }
}

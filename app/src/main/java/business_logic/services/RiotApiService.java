package business_logic.services;

import android.util.Log;

import java.io.IOException;

import business_logic.data_models.SummonerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiotApiService
{
    private static final String RIOT_API_KEY = "RGAPI-f617d2ba-cb1e-4cb6-8c93-55ae5cbf7a6e";
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

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response) {
                SummonerDTO summoner = response.body();
                Log.d("ID", summoner.getId());
                Log.d("ACCOUNT_ID", summoner.getAccountId());
                Log.d("LEVEL", summoner.getSummonerLevel() + "");
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t) {

            }
        });
    }
}

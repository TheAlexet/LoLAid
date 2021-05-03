package business_logic.services;

import android.app.Activity;
import android.util.Log;

import com.robrua.orianna.type.dto.summoner.Summoner;

import java.io.IOException;
import java.util.Set;

import business_logic.data_models.LeagueEntryDTO;
import business_logic.data_models.MatchDto;
import business_logic.data_models.SummonerDTO;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RiotApiService
{
    private final String RIOT_API_KEY = "RGAPI-ce8c5460-dc3f-4720-bbde-b9a4f06a3528";
    private final String RANKED_SOLO = "RANKED_SOLO_5x5";
    private final String RANKED_FLEX = "RANKED_FLEX_SR";

    private IRiotApiServiceREST service;
    private Activity activity;

    public RiotApiService(Activity activity)
    {
        this.activity = activity;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://euw1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);
    }

    /*
    public void printSummonerByNameTest()
    {
        Call<SummonerDTO> call = service.getSummonerByName("pabletefest", RIOT_API_KEY);
        Thread requestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    SummonerDTO summoner = call.execute().body();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        requestThread.start();
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

    public void getSummonerByName(String summonerName)
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

    public void getLeagueEntriesWithSummonerId(String encryptedSummonerId)
    {
        Call<Set<LeagueEntryDTO>> call =  service.getLeagueEntriesWithSummonerId("6NZOe5xujeQwYThyfSSxuc1j9Yo1q6BpB6qE0X6fUn_hRPY", RIOT_API_KEY);

        call.enqueue(new Callback<Set<LeagueEntryDTO>>() {
            @Override
            public void onResponse(Call<Set<LeagueEntryDTO>> call, Response<Set<LeagueEntryDTO>> response) {
                if (response.isSuccessful())
                {
                    Set<LeagueEntryDTO> leagueEntries = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (LeagueEntryDTO leagueEntry : leagueEntries)
                            {
                                Log.d("LEAGUE_ENTRY", leagueEntry.getSummonerName() + " " + leagueEntry.getRank() + " " + leagueEntry.getTier() + " - MiniSeries: " + leagueEntry.getMiniSeries());
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Set<LeagueEntryDTO>> call, Throwable t) {

            }
        });
    }

    public void getMatchByMatchId(String matchId)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);

        Call<MatchDto> call = service = retrofit.create(IRiotApiServiceREST.class);

        call.enqueue(new Callback<MatchDto>() {
            @Override
            public void onResponse(Call<MatchDto> call, Response<MatchDto> response) {
                if (response.isSuccessful())
                {
                    MatchDto match = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("MATCH", match.getGameDuration() + " | " + match.getQueueId() + " | " + match.getParticipantIdentities() + " | " + match.getTeams() + " | " + match.getParticipants());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MatchDto> call, Throwable t) {

            }
        });
    }
    */

    public void printSummonerInfoTest()
    {
        Observable<SummonerDTO> summonerObservable = service.getSummonerByName("pabletefest", RIOT_API_KEY);

        summonerObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(summoner -> {
                    Log.d("ID", summoner.getId());
                    Log.d("ACCOUNT_ID", summoner.getAccountId());
                    Log.d("LEVEL", summoner.getSummonerLevel() + "");
                    Log.d("PUUID", summoner.getPuuid());
                });
    }
}

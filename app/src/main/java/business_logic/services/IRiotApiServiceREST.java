package business_logic.services;

import business_logic.data_models.SummonerDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRiotApiServiceREST
{
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Call<SummonerDTO> getSummonerByName(@Path("summonerName") String summonerName, @Query("api_key") String apiKey);
}

package business_logic.services;

import java.util.List;
import java.util.Set;

import business_logic.data_models.ChampionMasteryDto;
import business_logic.data_models.CurrentGameInfo;
import business_logic.data_models.LeagueEntryDTO;
import business_logic.data_models.LeagueListDTO;
import business_logic.data_models.MatchDto;
import business_logic.data_models.MatchlistDto;
import business_logic.data_models.SummonerDTO;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRiotApiServiceREST
{
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Observable<SummonerDTO> getSummonerByName(@Path("summonerName") String summonerName, @Query("api_key") String apiKey);

    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}")
    Observable<List<ChampionMasteryDto>> getChampionsMasteryBySummonerId(@Path("encryptedSummonerId") String summonerId, @Query("api_key") String apiKey);

    @GET("/lol/league/v4/challengerleagues/by-queue/{queue}")
    Observable<LeagueListDTO> getChallengerLeagueByGivenQueue(@Path("queue") String queue, @Query("api_key") String apiKey);

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    Observable<Set<LeagueEntryDTO>> getLeagueEntriesWithSummonerId(@Path("encryptedSummonerId") String encryptedSummonerId, @Query("api_key") String apiKey);

    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    Observable<MatchlistDto> getMatchlistWithEncryptedAccountId(@Path("encryptedAccountId") String encryptedAccountId, @Query("api_key") String apiKey);

    @GET("/lol/match/v4/matches/{matchId}")
    Observable<MatchDto> getMatchByMatchId(@Path("matchId") String matchId, @Query("api_key") String apiKey);

    @GET("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    Observable<CurrentGameInfo> getCurrentGameInfoWithSummonerId(@Path("encryptedSummonerId") String encryptedSummonerId, @Query("api_key") String apiKey);
}

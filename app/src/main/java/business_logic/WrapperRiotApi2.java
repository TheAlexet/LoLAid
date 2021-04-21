package business_logic;

import android.util.Log;

import com.robrua.orianna.api.core.AsyncRiotAPI;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

import java.util.List;

public class WrapperRiotApi2
{
    public WrapperRiotApi2()
    {
        /*
        RiotAPI.setRegion(Region.EUW);
        RiotAPI.setAPIKey("RGAPI-433c9e0d-6a22-4908-839a-cbc78d99270");

        Summoner summoner = RiotAPI.getSummonerByName("FatalElement");
        Log.d("SUMMONER",summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");

        List<Champion> champions = RiotAPI.getChampions();
        Log.d("CHAMPS", "He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size() * Math.random())) + ".");

        League challenger = RiotAPI.getChallenger(QueueType.RANKED_SOLO_5x5);
        Summoner bestNA = challenger.getEntries().get(0).getSummoner();
        Log.d("BEST_NA","He's much better at writing Java code than he is at LoL. He'll never be as good as " + bestNA + ".");
        */

        AsyncRiotAPI.setRegion(Region.NA);
        AsyncRiotAPI.setAPIKey("RGAPI-433c9e0d-6a22-4908-839a-cbc78d99270");

        AsyncRiotAPI.getSummonerByName(new Action<Summoner>() {
            @Override
            public void perform(Summoner summoner) {
                Log.d("SUMMONER",summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");
            }

            public void handle(APIException e) {
                Log.d("ERROR_SUMMONER","Couldn't get summoner FatalElement");
            }
        }, "FatalElement");

        AsyncRiotAPI.getChampions(new Action<List<Champion>>() {
            @Override
            public void perform(List<Champion> champions) {
                Log.d("CHAMPS", "He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size() * Math.random())) + ".");
            }

            public void handle(APIException e) {
                Log.d("ERROR_CHAMPS","Couldn't get champion list.");
            }
        });

        AsyncRiotAPI.getChallenger(new Action<League>() {
            @Override
            public void perform(League challenger) {
                Summoner bestNA = challenger.getEntries().get(0).getSummoner();
                Log.d("BEST_NA","He's much better at writing Java code than he is at LoL. He'll never be as good as " + bestNA + ".");
            }

            public void handle(APIException e) {
                Log.d("ERROR_BEST_NA","Couldn't get solo queue challenger league.");
            }
        }, QueueType.RANKED_SOLO_5x5);

    }
}

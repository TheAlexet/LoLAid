package business_logic;

import android.util.Log;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.ChampionSpell;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.io.File;

public class WrapperRiotAPI
{
    private static final String RIOT_API_KEY = "RGAPI-433c9e0d-6a22-4908-839a-cbc78d99270";

    public WrapperRiotAPI()
    {
        //Orianna.Configuration config = new Orianna.Configuration();
        //Orianna.loadConfiguration("config.json");
        //Orianna.loadConfiguration(new File("G:" + File.separator + "DADM_Project" + File.separator+ "LoLAid" + File.separator + "app" + File.separator + "src" + File.separator + "main" + File.separator + "res" + File.separator + "config.json"));
        Orianna.setRiotAPIKey(RIOT_API_KEY);
        //Orianna.setDefaultLocale("en_US");
        //Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);
        //Orianna.setDefaultRegion(Region.NORTH_AMERICA);
    }

    public WrapperRiotAPI(Region region)
    {
        Orianna.setRiotAPIKey(RIOT_API_KEY);
        Orianna.setDefaultRegion(region);
    }

    public void setRegion(Region region)
    {
        Orianna.setDefaultRegion(region);
    }

    public void printSummonerInfoTest()
    {
        final Summoner summoner = Summoner.named("Tactical").withRegion(Region.NORTH_AMERICA).get();
        summoner.load();
        Log.d("LEVEL: ", summoner.getLevel() + "");
        Log.d("TIER: ", summoner.getHighestTier(Season.SEASON_9).toString());
        Log.d("ID: ", summoner.getId());
        Log.d("ACCOUNT_ID: ", summoner.getAccountId());
    }

    public void printChamps()
    {
        final Champions champions = Champions.withRegion(Region.NORTH_AMERICA).get();
        for(final Champion champion : champions) {
            Log.d("CHAMP", champion.getName() + " " + champion.getId());
        }
    }
}

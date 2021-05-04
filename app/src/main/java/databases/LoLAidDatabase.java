package databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import databases.models.Champion;
import databases.models.Rune;
import databases.models.SummonerSpell;

@Database(entities = {Champion.class, Rune.class, SummonerSpell.class}, version = 1)
public abstract class LoLAidDatabase extends RoomDatabase {

    // Singleton
    private static LoLAidDatabase lolAidDatabase;

    public synchronized static LoLAidDatabase getInstance(Context context) {
        if (lolAidDatabase == null) {
            lolAidDatabase = Room
                    .databaseBuilder(context, LoLAidDatabase.class, "LoLAid")
                    .build();
        }
        return lolAidDatabase;
    }

    public static LoLAidDatabase destroyInstance() {
        if (lolAidDatabase != null && lolAidDatabase.isOpen()) {
            lolAidDatabase.close();
            lolAidDatabase = null;
        }
        return lolAidDatabase;
    }

    public abstract ChampionDAO ChampionDAO();
    public abstract RuneDAO RuneDAO();
    public abstract SummonerSpellDAO SummonerSpellDAO();

}

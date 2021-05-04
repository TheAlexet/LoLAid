package databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import databases.models.SummonerSpell;

@Dao
public interface SummonerSpellDAO {

    @Insert
    long addSummonerSpell(SummonerSpell summonerSpell);

    @Query("SELECT * FROM " + DatabaseContract.SUMMONER_SPELL_TABLE_NAME)
    List<SummonerSpell> getSummonerSpellList();

    @Query("SELECT * FROM " + DatabaseContract.SUMMONER_SPELL_TABLE_NAME + " WHERE " + DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLICON + "= :summonerSpellId")
    int getSummonerSpellIcon(long summonerSpellId);

}

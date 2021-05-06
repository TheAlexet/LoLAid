package databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import databases.models.Champion;
import databases.models.SummonerSpell;

@Dao
public interface SummonerSpellDAO {

    @Insert
    void insertSummonerSpell(SummonerSpell summonerSpell);

    @Query("SELECT * FROM " + DatabaseContract.SUMMONER_SPELL_TABLE_NAME)
    List<SummonerSpell> getSummonerSpellList();

    @Query("SELECT * FROM " + DatabaseContract.SUMMONER_SPELL_TABLE_NAME + " WHERE " + DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLID + "= :summonerSpellId")
    SummonerSpell getSummonerSpell(long summonerSpellId);

    @Delete
    void deleteSummonerSpell(SummonerSpell summonerSpell);

    @Query("DELETE FROM " + DatabaseContract.SUMMONER_SPELL_TABLE_NAME)
    void deleteAllCRunes();
}

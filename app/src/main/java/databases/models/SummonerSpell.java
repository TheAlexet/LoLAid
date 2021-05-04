package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import databases.DatabaseContract;

@Entity(tableName = DatabaseContract.SUMMONER_SPELL_TABLE_NAME)
public class SummonerSpell {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_ID)
    private int id;

    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLID)
    @NonNull
    private long summonerSpellId;

    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLICON)
    @NonNull
    private int summonerSpellIcon;

    public SummonerSpell() {

    }

    public SummonerSpell(long summonerSpellId, int summonerSpellIcon) {
        this.summonerSpellId = summonerSpellId;
        this.summonerSpellIcon = summonerSpellIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public long getSummonerSpellId() {
        return summonerSpellId;
    }

    public void setSummonerSpellId(long newSummonerSpellId) {
        summonerSpellId = newSummonerSpellId;
    }

    public int getSummonerSpellIcon() {
        return summonerSpellIcon;
    }

    public void setSummonerSpellIcon(int newSummonerSpellIcon) { summonerSpellIcon = newSummonerSpellIcon; }
}

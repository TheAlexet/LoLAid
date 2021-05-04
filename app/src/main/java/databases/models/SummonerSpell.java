package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotation_table")
public class SummonerSpell {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "id")
    @NonNull
    private long summonerSpellId;

    @ColumnInfo(name = "icon", typeAffinity = ColumnInfo.BLOB)
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

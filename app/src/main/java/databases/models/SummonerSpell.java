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
    private int _ID;

    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLID)
    @NonNull
    private long key;

    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLNAME)
    @NonNull
    private String name;

    @ColumnInfo(name = DatabaseContract.SUMMONER_SPELL_COLUMN_NAME_SUMMONERSPELLICON)
    @NonNull
    private int iconId;

    public SummonerSpell() {

    }

    public SummonerSpell(int _ID, long key, @NonNull String name, int iconId) {
        this._ID = _ID;
        this.key = key;
        this.name = name;
        this.iconId = iconId;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}

package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonProperty;

import databases.DatabaseContract;

@Entity(tableName = DatabaseContract.CHAMPION_TABLE_NAME)
public class Champion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_ID)
    private int _ID;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONID)
    @NonNull
    private long key;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONNAME)
    @NonNull
    private String name;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONICON)
    @NonNull
    private int championIconId;

    public Champion() {

    }

    public Champion(int _ID, long key, @NonNull String name, int championIconId) {
        this._ID = _ID;
        this.key = key;
        this.name = name;
        this.championIconId = championIconId;
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

    public int getChampionIconId() {
        return championIconId;
    }

    public void setChampionIconId(int championIconId) {
        this.championIconId = championIconId;
    }
}

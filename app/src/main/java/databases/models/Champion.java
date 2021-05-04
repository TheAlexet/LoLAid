package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import databases.DatabaseContract;

@Entity(tableName = DatabaseContract.CHAMPION_TABLE_NAME)
public class Champion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_ID)
    private int id;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONID)
    @NonNull
    private long championId;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONNAME)
    @NonNull
    private String championName;

    @ColumnInfo(name = DatabaseContract.CHAMPION_COLUMN_NAME_CHAMPIONICON)
    @NonNull
    private int championIcon;

    public Champion() {

    }

    public Champion(int championId, String championName, int championIcon) {
        this.championId = championId;
        this.championName = championName;
        this.championIcon = championIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long newChampionId) {
        championId = newChampionId;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String newChampionName) {
        championName = newChampionName;
    }

    public int getChampionIcon() {
        return championIcon;
    }

    public void setChampionIcon(int newChampionIcon) { championIcon = newChampionIcon; }
}

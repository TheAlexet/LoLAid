package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotation_table")
public class Champion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "id")
    @NonNull
    private long championId;

    @ColumnInfo(name = "name")
    @NonNull
    private String championName;

    @ColumnInfo(name = "icon")
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

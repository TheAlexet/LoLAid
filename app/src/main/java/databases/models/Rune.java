package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import databases.DatabaseContract;

@Entity(tableName = DatabaseContract.RUNE_TABLE_NAME)
public class Rune {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_ID)
    private int id;

    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_RUNEID)
    @NonNull
    private long runeId;

    @ColumnInfo(name = DatabaseContract.RUNE_COLUMN_NAME_RUNEICON)
    @NonNull
    private int runeIcon;

    public Rune() {

    }

    public Rune(long runeId, int runeIcon) {
        this.runeId = runeId;
        this.runeIcon = runeIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public long getRuneId() {
        return runeId;
    }

    public void setRuneId(long newChampionId) {
        runeId = newChampionId;
    }

    public int getRuneIcon() {
        return runeIcon;
    }

    public void setRuneIcon(int newRuneIcon) { runeIcon = newRuneIcon; }
}

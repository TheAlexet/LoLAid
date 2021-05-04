package databases.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotation_table")
public class Rune {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    private int id;

    @ColumnInfo(name = "id")
    @NonNull
    private long runeId;

    @ColumnInfo(name = "icon", typeAffinity = ColumnInfo.BLOB)
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
